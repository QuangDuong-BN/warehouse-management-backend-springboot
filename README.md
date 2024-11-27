# ỨNG DỤNG QUẢN LÝ KHO HÀNG

## Mô Hình và Công Nghệ Sử Dụng

**Backend**: 
- Spring Boot
- Spring Data JPA
- Spring Security

**Android**: 
- Android Native (Java)

**DBMS**: 
- MySQL
---

## Giới Thiệu

Ứng dụng quản lý kho hàng giúp tối ưu hóa việc quản lý hàng hóa, sản phẩm, đơn đặt hàng và người dùng trong hệ thống. Phần mềm được thiết kế cho các trung tâm phân phối hoặc siêu thị lớn với 3 loại tài khoản người dùng chính: **Admin**, **Saler**, và **Stocker**. Mỗi loại tài khoản được phân quyền rõ ràng để thực hiện các chức năng tương ứng.

---

## Chức Năng Chính Và Thiết Kế Hệ Thống

### **Admin**
- Đăng ký và đăng nhập.
- Quản lý kho hàng: thêm, sửa, xóa kho hàng.
- Quản lý người dùng: thêm mới, cập nhật, xóa tài khoản **Saler** và **Stocker**.
- Quản lý danh sách siêu thị, sản phẩm và các đơn đặt hàng.

### **Stocker**
- Đăng nhập vào hệ thống.
- Quản lý sản phẩm: thêm, sửa, xóa sản phẩm trong kho.
- Xử lý đơn đặt hàng: theo dõi và cập nhật trạng thái đơn hàng.

### **Saler**
- Đăng nhập vào hệ thống.
- Quản lý danh sách kho hàng.
- Quản lý danh sách sản phẩm trong từng kho.

---

## Các Tài Khoản Người Dùng

### **Admin**
Người quản lý chính, có quyền cao nhất trong hệ thống. Admin chịu trách nhiệm quản lý tài khoản **Saler** và **Stocker**, cũng như toàn bộ hoạt động trong hệ thống, bao gồm kho hàng, sản phẩm, và người dùng.

### **Saler**
Người bán hàng, sau khi đăng nhập thành công, có thể quản lý danh sách các kho hàng mà mình phụ trách. Mỗi kho chứa danh sách các sản phẩm cần theo dõi và cập nhật.

### **Stocker**
Người quản lý kho hàng, thực hiện các chức năng quản lý sản phẩm trong kho và xử lý các đơn đặt hàng được đề xuất.


![Giao diện Saler](https://github.com/QuangDuong-BN/save-image-for-repo/blob/main/warehouse-management/usecase.png)

> *BIểu đồ Usecase*
---

## Hình Ảnh Minh Họa

### Giao Diện Ứng Dụng

![Giao diện Admin](https://github.com/QuangDuong-BN/save-image-for-repo/blob/main/warehouse-management/i1.png)
![Giao diện Admin](https://github.com/QuangDuong-BN/save-image-for-repo/blob/main/warehouse-management/i2.png)

> *Các hình ảnh trên minh họa giao diện chính của ứng dụng dành cho từng loại tài khoản: Admin, Stocker, và Saler.*

---

![Giao diện Admin](https://github.com/QuangDuong-BN/save-image-for-repo/blob/main/warehouse-management/i3.png)
![Giao diện Admin](https://github.com/QuangDuong-BN/save-image-for-repo/blob/main/warehouse-management/i4.png)
![Giao diện Admin](https://github.com/QuangDuong-BN/save-image-for-repo/blob/main/warehouse-management/i5.png)

> *Các hình ảnh trên minh họa giao diện chính của ứng dụng dành cho từng loại tài khoản: Admin, Stocker, và Saler.*

---
