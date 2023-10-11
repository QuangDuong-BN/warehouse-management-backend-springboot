package com.kma.warehouseManagement.controller;

import com.kma.warehouseManagement.entity.Storage;
import com.kma.warehouseManagement.entity.User;
import com.kma.warehouseManagement.service.StorageService;
import com.kma.warehouseManagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getList")
    public ResponseEntity<Iterable<User>> getList(HttpServletRequest request) throws AccessException {
        return ResponseEntity.ok(userService.getListUser(request));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(HttpServletRequest request,@RequestBody User user) throws AccessException {
        userService.addUser(request,user);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(HttpServletRequest request,@PathVariable Integer id) throws AccessException {
        userService.deleteUser(request,id);
        return ResponseEntity.ok("success");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(HttpServletRequest request,@RequestBody User user) throws AccessException {
        userService.updateUser(request,user);
        return ResponseEntity.ok("success");
    }





}
