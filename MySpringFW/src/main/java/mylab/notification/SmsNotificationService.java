package mylab.notification;

// SMS 알림 서비스 구현 클래스
public class SmsNotificationService implements NotificationService {

    private String provider;

    // 생성자를 통해 SMS 제공업체 주입
    public SmsNotificationService(String provider) {
        this.provider = provider;
    }

    // Getter
    public String getProvider() {
        return provider;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("[SMS 알림] " + message + " (제공업체: " + provider + ")");
    }
}
