package assignment;

public final class SoftwareEngineeringDept extends Department {

    public SoftwareEngineeringDept() {
    }

    public void swEng() {
        System.out.println("Custom software engineering");
    }

    @Override
    public String toString() {
        return "Software Engineering";
    }

}