package com.kma.warehouseManagement.myapplicationrunner;

import com.kma.warehouseManagement.config.JwtService;
import com.kma.warehouseManagement.entity.User;
import com.kma.warehouseManagement.enumCustom.RoleUser;
import com.kma.warehouseManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Công việc đã được thực hiện khi ứng dụng khởi động:");
        System.out.println("- Check và tạo admin nếu chưa được tạo.");
        Optional<User> numberOfAdmin = userRepository.findByUsername("ADMIN");
        if (numberOfAdmin.isEmpty()) {
            var user = User.builder()
                    .name("ADMIN")
                    .email("ADMIN.gmail.com")
                    .username("ADMIN")
                    .password(passwordEncoder.encode("ADMIN"))
                    .role(RoleUser.ADMIN)
                    .phone("123456789")
                    .storageId(null)
                    .marketId(null)
                    .build();
            userRepository.save(user);
        }

    }
}