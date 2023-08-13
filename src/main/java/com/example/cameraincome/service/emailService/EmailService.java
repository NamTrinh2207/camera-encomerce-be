package com.example.cameraincome.service.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendNewPasswordEmail(String recipientEmail, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Thông báo: Mật khẩu mới của bạn");
        String emailContent = "Chào bạn,\n"
                + "🔐 Chúng tôi rất vui thông báo rằng bạn đã yêu cầu khôi phục mật khẩu của mình.\n "
                + "Dưới đây là mật khẩu mới để bạn đăng nhập:\n"
                + "Mật khẩu mới: " + newPassword + "\n"
                + "Vui lòng thay đổi mật khẩu sau khi đăng nhập để đảm bảo tính bảo mật của tài khoản.\n"
                + "Trân trọng,\n"
                + "💼 Đội ngũ Hỗ trợ CameraMen";
        message.setText(emailContent);
        javaMailSender.send(message);
    }

    public String generateNewPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
