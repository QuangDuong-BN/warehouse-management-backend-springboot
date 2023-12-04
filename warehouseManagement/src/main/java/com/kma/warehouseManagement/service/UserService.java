package com.kma.warehouseManagement.service;

import com.kma.warehouseManagement.config.JwtService;
import com.kma.warehouseManagement.entity.Storage;
import com.kma.warehouseManagement.entity.User;
import com.kma.warehouseManagement.enumCustom.RoleUser;
import com.kma.warehouseManagement.exception.AccessDeniedException;
import com.kma.warehouseManagement.exception.EmailAlreadyExistException;
import com.kma.warehouseManagement.repository.StorageRepository;
import com.kma.warehouseManagement.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Integer getMarketIdByToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Loại bỏ "Bearer " từ token
            String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token
            return userRepository.findByUsername(username).get().getMarketId();
        }
        throw new NullPointerException("Token not provided or invalid");
    }


    public User getUserByToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Loại bỏ "Bearer " từ token
            String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token
            return userRepository.findByUsername(username).get();
        }
        throw new NullPointerException("Token not provided or invalid");
    }

    public String getUsernameByToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Loại bỏ "Bearer " từ token
            String username = jwtService.extractUsername(token); // Sử dụng JwtService để lấy username từ token
            return username;
        }
        return "Token not provided or invalid";
    }

    public List<Storage> getListStorage(HttpServletRequest request) throws AccessException {
        String username = getUsernameByToken(request);
        RoleUser role = userRepository.findByUsername(username).get().getRole();
        if (role == RoleUser.STOCKER) {
            throw new AccessDeniedException("You don't have permission to access this resource");
        }
        if (role == RoleUser.ADMIN) {
            return storageRepository.findAll();
        }
        return findAllByMarketId(userRepository.findByUsername(username).get().getMarketId());
    }

    private ArrayList<Storage> findAllByMarketId(Integer marketId) {
        return storageRepository.findAllByMarketId(marketId);
    }


    // service for Admin

    public Iterable<User> getListUser(HttpServletRequest request) throws AccessException {
        String username = getUsernameByToken(request);
        RoleUser role = userRepository.findByUsername(username).get().getRole();
        if (role == RoleUser.ADMIN) {
            return userRepository.findAllUser();
        } else {
            throw new AccessDeniedException("You are not an ADMIN account");
        }
    }

    public String addUser(HttpServletRequest request, @RequestBody User user) throws AccessException {
        String username = getUsernameByToken(request);
        if (userRepository.countByEmail(user.getEmail()) > 0 ||
                userRepository.countByUsername(user.getUsername()) > 0) {
            throw new EmailAlreadyExistException("Email or username already exists");
        }
//        if(userRepository.findByEmail(username).isPresent()){
//            throw new AccessDeniedException("Email already exists");
//        }
        RoleUser role = userRepository.findByUsername(username).get().getRole();
        if (role == RoleUser.ADMIN) {
            if (user.getRole() == RoleUser.ADMIN) {
                throw new AccessDeniedException("You are an ADMIN account but you can't add admin, you can only add stocker or saler");
            } else {
                userRepository.save(user);
                return "success";
            }
        } else {
            throw new AccessDeniedException("You are not an ADMIN account");
        }
    }

    public void deleteUser(HttpServletRequest request, Integer id) {
        String username = getUsernameByToken(request);
        RoleUser role = userRepository.findByUsername(username).get().getRole();
        if (role == RoleUser.ADMIN) {
            userRepository.deleteUserById(id);
        } else {
            throw new AccessDeniedException("You are not an ADMIN account");
        }
    }


    public void updateUser(HttpServletRequest request, User newUser) {
        String username = getUsernameByToken(request);
        RoleUser role = userRepository.findByUsername(username).get().getRole();

        if (role == RoleUser.ADMIN) {
            Optional<User> oldUser = userRepository.findByUsername(newUser.getUsername());
            newUser.setId(oldUser.get().getId());
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userRepository.save(newUser);
        } else {
            throw new AccessDeniedException("You are not an ADMIN account");
        }
    }
}
