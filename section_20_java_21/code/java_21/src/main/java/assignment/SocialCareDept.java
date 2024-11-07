package assignment;

public final class SocialCareDept extends Department {

    public SocialCareDept() {
    }

    public void socialCare() {
        System.out.println("Custom social care");
    }

    @Override
    public String toString() {
        return "Social Care";
    }

}
