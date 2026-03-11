package mylab.bank.control;

import mylab.bank.entity.Bank;
import mylab.bank.entity.SavingsAccount;
import mylab.bank.entity.CheckingAccount;
import mylab.bank.exception.AccountNotFOundExeption;
import mylab.bank.exception.InsufficientBalanceException;

// 은행 시스템의 기능을 테스트하는 클래스
public class BankDemo {

    public static void main(String[] args) {
        Bank bank = new Bank();

        // 1. 계좌 생성 테스트
        System.out.println("===== 계좌 생성 테스트 =====");
        SavingsAccount savings1 = bank.createSavingsAccount("홍길동", 100000, 0.05);
        SavingsAccount savings2 = bank.createSavingsAccount("김철수", 500000, 0.03);
        CheckingAccount checking1 = bank.createCheckingAccount("이영희", 300000, 100000);
        CheckingAccount checking2 = bank.createCheckingAccount("박지민", 200000, 50000);
        System.out.println();

        // 전체 계좌 출력
        bank.printAllAccounts();
        System.out.println();

        // 2. 입금/출금 테스트
        System.out.println("===== 입금/출금 테스트 =====");
        try {
            System.out.println("-- 입금 테스트 --");
            bank.deposit("1000", 50000);
            System.out.println();

            System.out.println("-- 출금 테스트 --");
            bank.withdraw("1000", 30000);
            System.out.println();
        } catch (AccountNotFOundExeption e) {
            System.out.println("오류: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("오류: " + e.getMessage());
        }

        // 3. 이자 적용 테스트
        System.out.println("===== 이자 적용 테스트 =====");
        System.out.println("이자 적용 전: " + savings1);
        savings1.applyInterest();
        System.out.println("이자 적용 후: " + savings1);
        System.out.println();

        // 4. 계좌 이체 테스트
        System.out.println("===== 계좌 이체 테스트 =====");
        try {
            bank.transfer("1000", "1002", 20000);
        } catch (AccountNotFOundExeption e) {
            System.out.println("오류: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("오류: " + e.getMessage());
        }
        System.out.println();

        // 5. 예외 처리 테스트
        System.out.println("===== 예외 처리 테스트 =====");

        // 5-1. 존재하지 않는 계좌 조회
        System.out.println("-- 존재하지 않는 계좌 조회 --");
        try {
            bank.findAccount("9999");
        } catch (AccountNotFOundExeption e) {
            System.out.println("오류: " + e.getMessage());
        }
        System.out.println();

        // 5-2. 잔액 부족 출금
        System.out.println("-- 잔액 부족 출금 테스트 --");
        try {
            bank.withdraw("1000", 99999999);
        } catch (AccountNotFOundExeption e) {
            System.out.println("오류: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("오류: " + e.getMessage());
        }
        System.out.println();

        // 5-3. 출금 한도 초과 (체킹 계좌)
        System.out.println("-- 출금 한도 초과 테스트 --");
        try {
            bank.withdraw("1002", 200000); // 한도 100000원 초과
        } catch (AccountNotFOundExeption e) {
            System.out.println("오류: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("오류: " + e.getMessage());
        }
        System.out.println();

        // 최종 계좌 정보 출력
        System.out.println("===== 최종 계좌 정보 =====");
        bank.printAllAccounts();
    }
}
