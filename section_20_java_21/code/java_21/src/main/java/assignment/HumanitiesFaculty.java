package assignment;

public final class HumanitiesFaculty extends Faculty {

    public HumanitiesFaculty() {
    }

    public void humanities() {
        System.out.println("We teach social care, European studies etc…");
    }

    @Override
    public String toString() {
        return "Humanities";
    }

}