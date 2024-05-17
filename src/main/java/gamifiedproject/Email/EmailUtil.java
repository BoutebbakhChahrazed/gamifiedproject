package gamifiedproject.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String gmail, String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(gmail);
        mimeMessageHelper.setSubject("Verify OTP");
        
        // Construct the email content with the OTP code
        String emailContent = String.format(
                "<div><p>Dear User,</p><p>Your OTP code is: <strong>%s</strong></p>"
                + "<p>Please use this code to verify your account.</p></div>", otp);

        mimeMessageHelper.setText(emailContent, true);

        javaMailSender.send(mimeMessage);
    }
}
