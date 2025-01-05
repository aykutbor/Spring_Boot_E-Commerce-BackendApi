package com.ecommerce.backend.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDTO {
        private String firstName;
        private String email;
        private String password;

}
