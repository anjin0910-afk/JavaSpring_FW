package mylab.notification.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import mylab.notification.EmailNotificationService;
import mylab.notification.NotificationManager;
import mylab.notification.SmsNotificationService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class, loader = AnnotationConfigContextLoader.class)
public class NotificationConfigTest {

    @Autowired
    NotificationManager notificationManager;

    @Test
    void testNotificationManager() {
        // a. NotificationManager가 Not Null 인지 검증
        assertNotNull(notificationManager);

        // b. 이메일 서비스 검증
        assertNotNull(notificationManager.getEmailService());
        EmailNotificationService emailService = (EmailNotificationService) notificationManager.getEmailService();
        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());

        // c. SMS 서비스 검증
        assertNotNull(notificationManager.getSmsService());
        SmsNotificationService smsService = (SmsNotificationService) notificationManager.getSmsService();
        assertEquals("SKT", smsService.getProvider());

        // d. NotificationManager의 메서드 실행
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}
