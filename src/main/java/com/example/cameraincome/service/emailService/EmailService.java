package com.example.cameraincome.service.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendNewPasswordEmail(String recipientEmail, String newPassword) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setTo(recipientEmail);
        helper.setSubject("Thông báo: Mật khẩu mới của bạn");
        String emailContent = "<div style='border: 1px solid #ddd; padding: 20px; text-align: center;'>"
                + "<h2>Chào bạn</h2>"
                + "<p>🔐 Chúng tôi thông báo rằng bạn đã yêu cầu khôi phục mật khẩu của mình.</p>"
                + "<p>Dưới đây là mật khẩu mới để bạn đăng nhập:</p>"
                + "<p><strong style='font-size: 17px;'>Mật khẩu mới: " + newPassword + "</strong></p>"
                + "<p>Vui lòng thay đổi mật khẩu sau khi đăng nhập để đảm bảo tính bảo mật của tài khoản.</p>"
                + "<h4>Trân trọng</h4>"
                + "<p>💼 Đội ngũ hỗ trợ CameraMen</p>"
                + "</div>";
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    public String generateNewPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
