package java17.assignment;

public abstract sealed class Department permits
        ComputerEngineeringDept, SoftwareEngineeringDept, AccountingDept, SocialCareDept {
}

final class ComputerEngineeringDept extends Department {
    public void compEng() {
        System.out.println("Custom computer engineering");
    }

    @Override
    public String toString() {
        return "Computer Engineering";
    }
}

final class SoftwareEngineeringDept extends Department {
    public void swEng() {
        System.out.println("Custom software engineering");
    }

    @Override
    public String toString() {
        return "Software Engineering";
    }
}

final class AccountingDept extends Department {
    public void accounting() {
        System.out.println("Custom accounting");
    }

    @Override
    public String toString() {
        return "Accounting";
    }
}

final class SocialCareDept extends Department {
    public void socialCare() {
        System.out.println("Custom social care");
    }

    @Override
    public String toString() {
        return "Social Care";
    }
}
