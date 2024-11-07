package assignment;

public final class EngineeringFaculty extends Faculty {

    public EngineeringFaculty() {
    }

    public void engineering() {
        System.out.println("We teach computer science, civil engineering etc…");
    }

    @Override
    public String toString() {
        return "Engineering";
    }

}
