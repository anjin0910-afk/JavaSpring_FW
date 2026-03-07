package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

// 체킹 계좌 - Account 클래스 상속
public class CheckingAccount extends Account {

    // 출금 한도
    private double withdrawalLimit;

    // 생성자
    public CheckingAccount(String accountNumber, String ownerName, double balance, double withdrawalLimit) {
        super(accountNumber, ownerName, balance);
        this.withdrawalLimit = withdrawalLimit;
    }

    // Getter & Setter
    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(double withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }

    // 출금 시 한도 초과 검사
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > withdrawalLimit) {
            throw new WithdrawalLimitExceededException(
                    "출금 한도를 초과했습니다. 출금 한도: " + withdrawalLimit + "원, 출금 요청: " + amount + "원");
        }
        super.withdraw(amount);
    }

    @Override
    public String toString() {
        return "[체킹계좌] " + super.toString() + ", 출금한도: " + withdrawalLimit + "원";
    }
}
