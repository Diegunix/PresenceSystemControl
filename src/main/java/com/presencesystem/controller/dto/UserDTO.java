package com.presencesystem.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String fingerprint;

}
