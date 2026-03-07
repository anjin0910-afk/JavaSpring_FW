package mylab.book.entity;

// 잡지 - Publication 상속
public class Magazine extends Publication {

    // 멤버변수
    private String publishPeriod;

    // 기본 생성자
    public Magazine() {
    }

    // 부모 생성자 호출 후 자신만의 속성 초기화
    public Magazine(String title, String publishDate, int page, int price, String publishPeriod) {
        super(title, publishDate, page, price);
        this.publishPeriod = publishPeriod;
    }

    // Getter & Setter
    public String getPublishPeriod() {
        return publishPeriod;
    }

    public void setPublishPeriod(String publishPeriod) {
        this.publishPeriod = publishPeriod;
    }

    // toString() 오버라이드: 부모 생성자 호출 후 자신만의 속성 초기화
    @Override
    public String toString() {
        return super.toString() + " [잡지] 발행주기:" + publishPeriod + ", "
                + getPage() + "쪽, " + getPrice() + "원, 출판일:" + getPublishDate();
    }
}
