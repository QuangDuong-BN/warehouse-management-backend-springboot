package com.kma.warehouseManagement.auth;

import com.kma.warehouseManagement.enumCustom.RoleUser;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String username;
    private String phone;
    private Integer storageId;
    private Integer marketId;
    private RoleUser role;
}
