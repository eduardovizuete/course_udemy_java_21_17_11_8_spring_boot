package assignment;

public final record LecturerRecord(String name, Integer age, Faculty faculty, Department dept) {

    // custom compact constructor
    public LecturerRecord {
        if (name.isBlank() || age.intValue() < 0) {
            String errorMsg = """
                    Illegal argument passed:
                        "name": %s,
                        "age": %s
                    """.formatted(name, age);
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public boolean hasPhd() {
        switch (name) {
            case String s when name.toUpperCase().startsWith("DR.") -> {
                return true;
            }
            case String s when name.toUpperCase().endsWith("PHD") -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public void whichFaculty() {
        switch (faculty) {
            // switch expression pattern matching
            case EngineeringFaculty eng -> {
                System.out.println("Faculty of: " + eng);
                eng.engineering();
            }
            case HumanitiesFaculty hum -> {
                System.out.println("Faculty of: " + hum);
                hum.humanities();
            }
            case BusinessFaculty bus -> {
                System.out.println("Faculty of: " + bus);
                bus.business();
            }
            default -> throw new IllegalArgumentException("Invalid Faculty: " + faculty);
        }
    }

    public void whichDept() {
        switch (dept) {
            // switch expression pattern matching
            case ComputerEngineeringDept com -> {
                System.out.println("Dept of: " + com);
                com.compEng();
            }
            case SoftwareEngineeringDept sof -> {
                System.out.println("Dept of: " + sof);
                sof.swEng();
            }
            case SocialCareDept soc -> {
                System.out.println("Dept of: " + soc);
                soc.socialCare();
            }
            case AccountingDept acc -> {
                System.out.println("Dept of: " + acc);
                acc.accounting();
            }
            default -> throw new IllegalArgumentException("Invalid Department: " + dept);
        }
    }

}
