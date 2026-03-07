package mylab.notification;

// 이메일과 SMS 알림 서비스를 관리하는 중앙 클래스
public class NotificationManager {

    private NotificationService emailService;
    private NotificationService smsService;

    // 생성자를 통해 이메일 서비스와 SMS 서비스 주입
    public NotificationManager(NotificationService emailService, NotificationService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    // Getter
    public NotificationService getEmailService() {
        return emailService;
    }

    public NotificationService getSmsService() {
        return smsService;
    }

    // 이메일로 알림 전송
    public void sendNotificationByEmail(String message) {
        emailService.sendNotification(message);
    }

    // SMS로 알림 전송
    public void sendNotificationBySms(String message) {
        smsService.sendNotification(message);
    }
}
