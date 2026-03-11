package mylab.bank.exception;

// 계좌를 찾을 수 없을 때 발생하는 예외 클래스
public class AccountNotFOundExeption extends Exception {

    public AccountNotFOundExeption(String message) {
        super(message);
    }
}
