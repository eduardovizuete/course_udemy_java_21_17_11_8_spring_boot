package assignment;

public final class ComputerEngineeringDept extends Department {

    public ComputerEngineeringDept() {
    }

    public void compEng() {
        System.out.println("Custom computer engineering");
    }

    @Override
    public String toString() {
        return "Computer Engineering";
    }

}
