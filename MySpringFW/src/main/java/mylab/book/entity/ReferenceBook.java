package mylab.book.entity;

// 참고서 - Publication 상속
public class ReferenceBook extends Publication {

    // 멤버변수
    private String field;

    // 기본 생성자
    public ReferenceBook() {
    }

    // 매개변수 생성자
    public ReferenceBook(String title, String publishDate, int page, int price, String field) {
        super(title, publishDate, page, price);
        this.field = field;
    }

    // Getter & Setter
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    // toString() 오버라이드
    @Override
    public String toString() {
        return super.toString() + " [참고서] 분야:" + field + ", "
                + getPage() + "쪽, " + getPrice() + "원, 출판일:" + getPublishDate();
    }
}
