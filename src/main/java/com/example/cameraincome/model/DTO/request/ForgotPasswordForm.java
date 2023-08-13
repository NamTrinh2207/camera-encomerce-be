package com.example.cameraincome.model.DTO.request;

public class ForgotPasswordForm {
    private String email;

    public ForgotPasswordForm() {
    }

    public ForgotPasswordForm(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
