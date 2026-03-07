package mylab.bank.entity;

// 저축 계좌 - Account 클래스 상속
public class SavingsAccount extends Account {

    // 이자율
    private double interestRate;

    // 생성자
    public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        this.interestRate = interestRate;
    }

    // Getter & Setter
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // 이자 적용
    public void applyInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
        System.out.println("이자 적용 완료! 이자율: " + (interestRate * 100) + "%, 이자: " + interest + "원");
    }

    @Override
    public String toString() {
        return "[저축계좌] " + super.toString() + ", 이자율: " + (interestRate * 100) + "%";
    }
}
