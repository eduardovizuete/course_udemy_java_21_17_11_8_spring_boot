package assignment;

public final class BusinessFaculty extends Faculty {

    public BusinessFaculty() {
    }

    public void business() {
        System.out.println("We teach accountancy, law, economics etc…");
    }

    @Override
    public String toString() {
        return "Business";
    }

}
