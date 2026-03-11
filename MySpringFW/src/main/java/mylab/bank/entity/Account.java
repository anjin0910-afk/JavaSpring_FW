package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

// 모든 계좌의 기본이 되는 추상 클래스
public abstract class Account {

    // 멤버변수 (private)
    private String accountNumber;
    private String ownerName;
    private double balance;

    // 생성자
    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    // Getter & Setter
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // 입금
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println(amount + "원이 입금되었습니다. 현재 잔액: " + this.balance + "원");
        } else {
            System.out.println("입금 금액은 0보다 커야 합니다.");
        }
    }

    // 출금
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "잔액이 부족합니다. 현재 잔액: " + balance + "원, 출금 요청: " + amount + "원");
        }
        this.balance -= amount;
        System.out.println(amount + "원이 출금되었습니다. 현재 잔액: " + this.balance + "원");
    }

    @Override
    public String toString() {
        return "계좌번호: " + accountNumber
                + ", 소유자: " + ownerName
                + ", 잔액: " + balance + "원";
    }
}
