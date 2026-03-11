package mylab.library.control;

import java.util.List;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        // a. 도서관 객체 생성
        Library library = new Library("중앙 도서관");

        // b. 샘플 도서 추가
        addSampleBooks(library);

        // c. 도서관 정보 출력
        System.out.println("===== " + library.getName() + " =====");
        System.out.println("전체 도서 수: " + library.getTotalBooks());
        System.out.println("대출 가능 도서 수: " + library.getAvailableBooksCount());
        System.out.println("대출 중인 도서 수: " + library.getBorrowedBooksCount());
        System.out.println();

        // d. 각 기능 테스트
        testFindBook(library);
        testCheckOut(library);
        testReturn(library);

        // e. 대출 가능한 도서 목록 출력
        displayAvailableBooks(library);
    }

    // 샘플 도서 목록을 도서관에 추가하는 메서드
    private static void addSampleBooks(Library library) {
        library.addBook(new Book("자바 프로그래밍", "김자바", "978-89-01-12345-6", 2022));
        library.addBook(new Book("객체지향의 사실과 오해", "조영호", "978-89-01-67890-1", 2015));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        library.addBook(new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        library.addBook(new Book("자바의 정석", "남궁성", "978-89-01-14077-4", 2019));
    }

    // 제목과 저자로 도서를 검색하는 기능을 테스트하는 메서드
    private static void testFindBook(Library library) {
        System.out.println("===== 도서 검색 테스트 =====");

        // 제목으로 검색
        System.out.println("제목으로 검색 결과:");
        Book foundBook = library.findBookByTitle("자바의 정석");
        if (foundBook != null) {
            System.out.println(foundBook);
        } else {
            System.out.println("검색 결과 없음");
        }
        System.out.println();

        // 저자로 검색
        System.out.println("저자로 검색 결과:");
        List<Book> foundBooks = library.findBooksByAuthor("Robert C. Martin");
        for (Book book : foundBooks) {
            System.out.println(book);
        }
        System.out.println();
    }

    // 도서 대출 기능을 테스트하는 메서드
    private static void testCheckOut(Library library) {
        System.out.println("===== 도서 대출 테스트 =====");

        String isbn = "978-89-01-14077-4";
        boolean result = library.checkOutBook(isbn);
        if (result) {
            System.out.println("도서 대출 성공!");
            System.out.println("대출된 도서 정보:");
            System.out.println(library.findBookByISBN(isbn));
        } else {
            System.out.println("도서 대출 실패!");
        }

        System.out.println();
        System.out.println("도서관 현재 상태:");
        System.out.println("전체 도서 수: " + library.getTotalBooks());
        System.out.println("대출 가능 도서 수: " + library.getAvailableBooksCount());
        System.out.println("대출 중인 도서 수: " + library.getBorrowedBooksCount());
        System.out.println();
    }

    // 도서 반납 기능을 테스트하는 메서드
    private static void testReturn(Library library) {
        System.out.println("===== 도서 반납 테스트 =====");

        String isbn = "978-89-01-14077-4";
        boolean result = library.returnBook(isbn);
        if (result) {
            System.out.println("도서 반납 성공!");
            System.out.println("반납된 도서 정보:");
            System.out.println(library.findBookByISBN(isbn));
        } else {
            System.out.println("도서 반납 실패!");
        }

        System.out.println();
        System.out.println("도서관 현재 상태:");
        System.out.println("전체 도서 수: " + library.getTotalBooks());
        System.out.println("대출 가능 도서 수: " + library.getAvailableBooksCount());
        System.out.println("대출 중인 도서 수: " + library.getBorrowedBooksCount());
    }

    // 대출 가능한 도서 목록을 출력하는 메서드
    private static void displayAvailableBooks(Library library) {
        System.out.println("===== 대출 가능한 도서 목록 =====");
        List<Book> availableBooks = library.getAvailableBooks();
        for (Book book : availableBooks) {
            System.out.println(book);
            System.out.println("------------------------");
        }
    }
}
