package vn.edu.fpt.BeautyCenter.service;

public interface EmailService {
    void sendVerificationEmail(String toEmail);

    void sendPasswordResetEmail(String email, String otp);
}
