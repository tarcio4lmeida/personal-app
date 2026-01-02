package com.example.personal_api.exception;

public record ApiError(
        int status,
        String message
) {}