package vn.edu.fpt.BeautyCenter.service;

import vn.edu.fpt.BeautyCenter.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    void registerUser(String fullName, String username, String email, String phone, String password);
    void verifyEmail(String email);
    Optional<User> findByUsernameAndPassword(String username, String password);
    void sendPasswordResetEmail(String email);

    User verifyOtp(String otp);

    void resetPassword(String email, String password);

    String getUserName(String serviceId);


    Map<String, String> getAllAuthorsMap();
}
