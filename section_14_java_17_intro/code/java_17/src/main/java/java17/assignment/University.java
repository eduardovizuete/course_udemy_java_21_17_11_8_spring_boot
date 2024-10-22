package java17.assignment;

public class University {

    public static void main (String[] args) {
        // 1. Force an exception
        //LecturerRecord recOne = new LecturerRecord("", 12, new EngineeringFaculty(), new ComputerEngineeringDept());
        //LecturerRecord recTwo = new LecturerRecord("Joe Bloggs", -12, new EngineeringFaculty(), new ComputerEngineeringDept());

        // 2. Create a valid lectured - output details using toString() and individual accessor methods
        LecturerRecord recJane = new LecturerRecord("Jane Bloggs", 24, new EngineeringFaculty(), new SoftwareEngineeringDept());
        System.out.println(recJane);

        System.out.printf("Name is %s %n", recJane.name());
        System.out.printf("Age is %s %n", recJane.age());
        System.out.printf("Faculty is %s %n", recJane.faculty());
        System.out.printf("Department is %s %n", recJane.dept());

        recJane.whichFaculty();
        recJane.whichDept();
        System.out.printf("Phd? %s %n", recJane.hasPhd());

        LecturerRecord anne = new LecturerRecord("Dr. Anne Bloggs", 35, new BusinessFaculty(), new AccountingDept());
        System.out.println(anne);
        System.out.println(anne.hasPhd() ? "Anne has a PhD" : "Anne has not a PhD");

        LecturerRecord joe = new LecturerRecord("Joe Bloggs PhD", 54, new HumanitiesFaculty(), new SocialCareDept());
        System.out.println(joe);
        System.out.println(joe.hasPhd() ? "Joe has a PhD" : "Joe has not a PhD");
    }

}
