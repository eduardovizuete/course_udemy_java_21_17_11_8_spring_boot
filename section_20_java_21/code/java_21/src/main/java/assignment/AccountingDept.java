package assignment;

public final class AccountingDept extends Department {

    public AccountingDept() {
    }

    public void accounting() {
        System.out.println("Custom accounting");
    }

    @Override

    public String toString() {
        return "Accounting";
    }

}