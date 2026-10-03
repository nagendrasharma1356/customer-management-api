package com.example.customerjwt.dto;

public record AuthResponse(String token, String tokenType, String username, String role) {
}
