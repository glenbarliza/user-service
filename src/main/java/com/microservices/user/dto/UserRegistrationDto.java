package com.microservices.user.dto;

public record UserRegistrationDto(
        String name,
        String email,
        String phone,
        String department
) {}
