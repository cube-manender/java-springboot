package com.example.firstProject.utils;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UserUtility {
    @NotNull
    public static String generateRandomString() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}
