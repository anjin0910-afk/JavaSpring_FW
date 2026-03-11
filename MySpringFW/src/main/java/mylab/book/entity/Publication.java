package mylab.book.entity;

// 모든 출판물의 기본 클래스
public class Publication {

    // 멤버변수
    private String title;
    private String publishDate;
    private int page;
    private int price;

    // 기본 생성자: 빈 객체 생성
    public Publication() {
    }

    // 매개변수 생성자: 모든 필드 초기화
    public Publication(String title, String publishDate, int page, int price) {
        this.title = title;
        this.publishDate = publishDate;
        this.page = page;
        this.price = price;
    }

    // Getter & Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // toString: title만 반환
    @Override
    public String toString() {
        return title;
    }
}
