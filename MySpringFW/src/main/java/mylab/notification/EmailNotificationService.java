package mylab.notification;

// 이메일 알림 서비스 구현 클래스
public class EmailNotificationService implements NotificationService {

    private String smtpServer;
    private int port;

    // 생성자를 통해 SMTP 서버 주소와 포트 번호 주입
    public EmailNotificationService(String smtpServer, int port) {
        this.smtpServer = smtpServer;
        this.port = port;
    }

    // Getter
    public String getSmtpServer() {
        return smtpServer;
    }

    public int getPort() {
        return port;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("[이메일 알림] " + message + " (SMTP: " + smtpServer + ", Port: " + port + ")");
    }
}
