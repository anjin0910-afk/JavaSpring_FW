package mylab.book.entity;

// 소설 - Publication 상속
public class Novel extends Publication {

    // 멤버변수
    private String author;
    private String genre;

    // 기본 생성자
    public Novel() {
    }

    // 매개변수 생성자
    public Novel(String title, String publishDate, int page, int price, String author, String genre) {
        super(title, publishDate, page, price);
        this.author = author;
        this.genre = genre;
    }

    // Getter & Setter
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // toString() 오버라이드
    @Override
    public String toString() {
        return super.toString() + " [소설] 저자:" + author + ", 장르:" + genre + ", "
                + getPage() + "쪽, " + getPrice() + "원, 출판일:" + getPublishDate();
    }
}
