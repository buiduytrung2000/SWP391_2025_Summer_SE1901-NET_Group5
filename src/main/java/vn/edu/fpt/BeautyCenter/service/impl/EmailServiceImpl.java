package vn.edu.fpt.BeautyCenter.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.edu.fpt.BeautyCenter.service.EmailService;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    public static final String UTF_8_ENCODING = "UTF-8";
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Override
    public void sendVerificationEmail(String toEmail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);

            helper.setPriority(1);
            helper.setSubject("Xác thực tài khoản");
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);

            String verifyUrl = "http://localhost:8081/sbs/register/verify?email=" + toEmail;

            String htmlContent = "<p>Vui lòng xác thực tài khoản của bạn bằng cách nhấn vào liên kết dưới đây:</p>" +
                    "<a href=\"" + verifyUrl + "\">Xác thực</a>";
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException exception) {
            if (exception.getMessage().contains("Recipient address rejected")) {
                throw new RuntimeException("Recipient address rejected: The email address does not exist.");
            } else {
                throw new RuntimeException("Failed to send verification email: " + exception.getMessage());
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void sendPasswordResetEmail(String email, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);

            helper.setPriority(1);
            helper.setSubject("Yêu cầu đổi mật khẩu");
            helper.setFrom(fromEmail);
            helper.setTo(email);

            helper.setText("Mã xác thực để đổi mật khẩu của bạn là: " + otp, true);
            mailSender.send(message);
        } catch (MessagingException exception) {
            if (exception.getMessage().contains("Recipient address rejected")) {
                throw new RuntimeException("Recipient address rejected: The email address does not exist.");
            } else {
                throw new RuntimeException("Failed to send verification email: " + exception.getMessage());
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
