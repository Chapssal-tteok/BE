package com.chapssal_tteok.preview.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String username;

    private String accessToken;

    private String refreshToken;
}
