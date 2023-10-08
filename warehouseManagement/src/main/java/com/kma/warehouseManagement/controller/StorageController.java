package com.kma.warehouseManagement.controller;

import com.kma.warehouseManagement.entity.Storage;
import com.kma.warehouseManagement.service.StorageService;
import com.kma.warehouseManagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserService userService;

    @GetMapping("/getList")
    public ResponseEntity<Iterable<Storage>> getList(HttpServletRequest request) throws AccessException {
        return ResponseEntity.ok(userService.getListStorage(request));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStorage(@RequestBody Storage storage) {
        storageService.addStorage(storage);
        return ResponseEntity.ok("success");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStorage(@RequestBody Storage storage) {
        storageService.updateStorage(storage.getId(), storage);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStorage(@PathVariable("id") Integer id) {
        storageService.deleteStorage(id);
        return ResponseEntity.ok("success");
    }
}
