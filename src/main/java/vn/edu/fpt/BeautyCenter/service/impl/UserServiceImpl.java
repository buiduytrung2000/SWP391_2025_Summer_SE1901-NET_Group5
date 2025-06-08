package vn.edu.fpt.BeautyCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.fpt.BeautyCenter.entity.PasswordResetToken;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.Role;
import vn.edu.fpt.BeautyCenter.entity.enums.UserStatus;
import vn.edu.fpt.BeautyCenter.repository.PasswordResetTokenRepository;
import vn.edu.fpt.BeautyCenter.repository.UserRepository;
import vn.edu.fpt.BeautyCenter.service.EmailService;
import vn.edu.fpt.BeautyCenter.service.UserService;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public void registerUser(String fullName, String username, String email, String phone, String password) {
        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setRole(Role.customer);
        user.setStatus(UserStatus.inactive);
        userRepository.save(user);
        emailService.sendVerificationEmail(email);
    }

    @Override
    public void verifyEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        user.setStatus(UserStatus.active);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public void sendPasswordResetEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        String otp = generateOtp();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetToken.setToken(otp);
        passwordResetToken.setUsed(false);
        passwordResetToken.setExpiration(LocalDateTime.now().plusMinutes(5)); // 5 phut
        passwordResetTokenRepository.save(passwordResetToken);
        emailService.sendPasswordResetEmail(email, otp);
    }

    @Override
    public User verifyOtp(String otp) {
        Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository.findByToken(otp);
        if(passwordResetToken.isEmpty())
            return null;
        PasswordResetToken token = passwordResetToken.get();

        if (!token.isUsed() && token.getExpiration().isAfter(LocalDateTime.now())) {
            token.setUsed(true);
            passwordResetTokenRepository.save(token);
            return token.getUser();
        }
        return null;
    }

    @Override
    public void resetPassword(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {


        userRepository.save(user);
    }

    @Override
    public String getUserName(String serviceId) {
        if(serviceId == null) return null;
        return userRepository.findById(serviceId).map(User::getUsername).orElse("Unknown User");
    }

    private String generateOtp() {
        StringBuilder otp = new StringBuilder(6);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            otp.append(digit);
        }
        return otp.toString();
    }
}
