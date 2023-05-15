package com.tyagi.springsecurity6.record;


@SuppressWarnings("unused")
public record UserRecord(String firstName, String lastName, String email, String username, String dob, Boolean isActive, String role) {
}
