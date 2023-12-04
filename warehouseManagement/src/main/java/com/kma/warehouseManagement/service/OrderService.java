package com.kma.warehouseManagement.service;

import com.kma.warehouseManagement.entity.Item;
import com.kma.warehouseManagement.entity.Order;
import com.kma.warehouseManagement.entityDto.OrderDto;
import com.kma.warehouseManagement.entityDto.ProductDto2;
import com.kma.warehouseManagement.enumCustom.RoleUser;
import com.kma.warehouseManagement.enumCustom.StatusOrder;
import com.kma.warehouseManagement.exception.AccessDeniedException;
import com.kma.warehouseManagement.exception.BadRequestException;
import com.kma.warehouseManagement.repository.ItemRepository;
import com.kma.warehouseManagement.repository.OrderRepository;
import com.kma.warehouseManagement.repository.ProductRepository;
import com.kma.warehouseManagement.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;


    public static int generateRandomNumber() {
        Random random = new Random();
        // Sinh một số nguyên ngẫu nhiên từ 10000 đến 99999 (bao gồm cả 10000 và 99999)
        int randomNumber = random.nextInt(90000) + 10000;
        return randomNumber;
    }

    public List<ProductDto2> getLineItemByOderId(Integer orderId) {
        List<Item> items = itemRepository.findByOrderId(orderId);
        List<ProductDto2> productDto2s = new ArrayList<>();
        for (int i = 0, n = items.size(); i < n; i++) {
            ProductDto2 productDto2 = new ProductDto2();
            productDto2.setId(items.get(i).getProductId());
            productDto2.setSoLuong(items.get(i).getSoLuong());
            productDto2s.add(productDto2);
        }
        return productDto2s;
    }


    public List<OrderDto> getList(HttpServletRequest request) {
//        String username = userService.getUsernameByToken(request);
//        RoleUser role = userRepository.findByUsername(username).get().getRole();
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (int i = 0, n = orders.size(); i < n; i++) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orders.get(i).getId());
            orderDto.setOwnerId(orders.get(i).getOwnerId());
            orderDto.setLineItems(getLineItemByOderId(orders.get(i).getItemId()));
            orderDto.setStatus(orders.get(i).getStatus());
            orderDto.setOption(orders.get(i).getOption());
            orderDto.setMessage(orders.get(i).getMessage());
            orderDto.setStorageId(orders.get(i).getStorageId());
            orderDto.setMarketId(orders.get(i).getMarketId());
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }

    public void addOrder(HttpServletRequest request, OrderDto orderDto) {
        String username = userService.getUsernameByToken(request);
        RoleUser role = userRepository.findByUsername(username).get().getRole();
//        if (role == RoleUser.SALER) {
//            throw new AccessDeniedException("You don't have permission to access this resource");
//        }
        // check so luong:
        List<ProductDto2> productDto2List = orderDto.getLineItems();
        for (int i = 0; i < productDto2List.size(); i++) {
            Integer id = productDto2List.get(i).getId();
            Integer soLuong = productDto2List.get(i).getSoLuong();
            if (productRepository.findByIdAndStorageId(id, orderDto.getStorageId()).isEmpty()) {
                throw new BadRequestException("So luong trong kho khong du");
            }
            Integer soLuongTrongKho = productRepository.findByIdAndStorageId(id, orderDto.getStorageId()).get().getSoLuong();
            if (soLuongTrongKho < soLuong) {
                throw new BadRequestException("So luong trong kho khong du");
            }
        }

        // tru so luong trong kho
        for (int i = 0; i < productDto2List.size(); i++) {
            Integer id = productDto2List.get(i).getId();
            Integer soLuong = productDto2List.get(i).getSoLuong();
            Integer soLuongTrongKho = productRepository.findByIdAndStorageId(id, orderDto.getStorageId()).get().getSoLuong();
            Integer soLuongConLai = soLuongTrongKho - soLuong;
            productRepository.updateProductById(id, soLuongConLai);
        }

        Integer itemId = generateRandomNumber();
        Order order = new Order();
        order.setOwnerId(userRepository.findByUsername(username).get().getId());
        order.setItemId(itemId);
        order.setStatus(StatusOrder.WAITING);
        order.setOption(orderDto.getOption());
        order.setMessage(orderDto.getMessage());
        order.setStorageId(orderDto.getStorageId());
        order.setMarketId(orderDto.getMarketId());
        orderRepository.save(order);

        // luu lineItems
        List<ProductDto2> productDto2 = orderDto.getLineItems();
        for (int i = 0, n = productDto2.size(); i < n; i++) {
            Item item = new Item();
            item.setOrderId(itemId);
            item.setProductId(productDto2.get(i).getId());
            item.setSoLuong(productDto2.get(i).getSoLuong());
            itemRepository.save(item);
        }
    }

    //Accepted Order
    public void acceptOrder(HttpServletRequest request, Integer id) {
        String username = userService.getUsernameByToken(request);
        RoleUser role = userRepository.findByUsername(username).get().getRole();
        if (role == RoleUser.SALER) {
            throw new AccessDeniedException("You don't have permission to access this resource");
        }
        Order order = orderRepository.findById(id).get();
        order.setStatus(StatusOrder.ACCEPTED);
        orderRepository.save(order);
    }

    // cancel Order
    public void cancelOrder(HttpServletRequest request, Integer id) {
        String username = userService.getUsernameByToken(request);
        RoleUser role = userRepository.findByUsername(username).get().getRole();
        if (role == RoleUser.SALER) {
            throw new AccessDeniedException("SALER don't have permission to access this resource");
        }
        Order order = orderRepository.findById(id).get();
        order.setStatus(StatusOrder.CANCELLED);
        orderRepository.save(order);

        // cong so luong trong kho
        List<ProductDto2> productDto2List = getLineItemByOderId(orderRepository.findById(id).get().getItemId());
        System.out.println(productDto2List.toString());
        for (int i = 0; i < productDto2List.size(); i++) {
            Integer idProduct = productDto2List.get(i).getId();
            Integer soLuong = productDto2List.get(i).getSoLuong();
            System.out.println(soLuong);
            Integer soLuongTrongKho = productRepository.findByIdAndStorageId(idProduct, order.getStorageId()).get().getSoLuong();
            System.out.println(soLuongTrongKho);
            Integer soLuongConLai = soLuongTrongKho + soLuong;
            System.out.println(soLuongConLai);
            productRepository.updateProductById(idProduct, soLuongConLai);
        }
    }

//    public void updateOrderById(HttpServletRequest request, Integer id, OrderDto orderDto) {
//        cancelOrder(request, id);
//
//
//        String username = userService.getUsernameByToken(request);
//        RoleUser role = userRepository.findByUsername(username).get().getRole();
//        if (role == RoleUser.SALER) {
//            throw new AccessDeniedException("You don't have permission to access this resource");
//        }
//        Order order = orderRepository.findById(id).get();
//        order.setOwnerId(orderDto.getOwnerId());
//        order.setItemId(orderDto.getItemId());
//        order.setStatus(orderDto.getStatus());
//        order.setOption(orderDto.getOption());
//        order.setMessage(orderDto.getMessage());
//        order.setStorageId(orderDto.getStorageId());
//        order.setMarketId(orderDto.getMarketId());
//        orderRepository.save(order);
//    }

    public void deleteOrderById(Integer id) {
        Integer lineItems = orderRepository.findById(id).get().getItemId();
        itemRepository.deleteByOrderId(lineItems);
        orderRepository.deleteById(id);
    }
}
