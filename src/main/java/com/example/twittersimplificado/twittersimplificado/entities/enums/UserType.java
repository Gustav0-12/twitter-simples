package com.example.twittersimplificado.twittersimplificado.entities.enums;

public enum UserType {
    ADMIN("admin"),
    COMMON("commom");

    private String userType;
    UserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }
}
