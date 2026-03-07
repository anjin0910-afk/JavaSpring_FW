package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFOundExeption;
import mylab.bank.exception.InsufficientBalanceException;

// 은행 시스템의 주요 관리 클래스
public class Bank {

    // 멤버변수 (private)
    private List<Account> accounts;
    private int nextAccountNumber;

    // 생성자
    public Bank() {
        this.accounts = new ArrayList<>();
        this.nextAccountNumber = 1000;
    }

    // 저축(Savings) 계좌 생성
    public SavingsAccount createSavingsAccount(String ownerName, double initialBalance, double interestRate) {
        String accountNumber = String.valueOf(nextAccountNumber++);
        SavingsAccount account = new SavingsAccount(accountNumber, ownerName, initialBalance, interestRate);
        accounts.add(account);
        System.out.println("저축 계좌가 생성되었습니다. " + account);
        return account;
    }

    // 체킹(Checking) 계좌 생성
    public CheckingAccount createCheckingAccount(String ownerName, double initialBalance, double withdrawalLimit) {
        String accountNumber = String.valueOf(nextAccountNumber++);
        CheckingAccount account = new CheckingAccount(accountNumber, ownerName, initialBalance, withdrawalLimit);
        accounts.add(account);
        System.out.println("체킹 계좌가 생성되었습니다. " + account);
        return account;
    }

    // 계좌번호로 계좌 조회
    public Account findAccount(String accountNumber) throws AccountNotFOundExeption {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFOundExeption("계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
    }

    // 입금
    public void deposit(String accountNumber, double amount) throws AccountNotFOundExeption {
        Account account = findAccount(accountNumber);
        account.deposit(amount);
    }

    // 출금
    public void withdraw(String accountNumber, double amount)
            throws AccountNotFOundExeption, InsufficientBalanceException {
        Account account = findAccount(accountNumber);
        account.withdraw(amount);
    }

    // 계좌 이체
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount)
            throws AccountNotFOundExeption, InsufficientBalanceException {
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        System.out.println("이체 완료! [" + fromAccountNumber + "] -> [" + toAccountNumber + "] " + amount + "원");
    }

    // 모든 계좌 정보 출력
    public void printAllAccounts() {
        System.out.println("===== 전체 계좌 목록 =====");
        if (accounts.isEmpty()) {
            System.out.println("등록된 계좌가 없습니다.");
        } else {
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
        System.out.println("========================");
    }
}
