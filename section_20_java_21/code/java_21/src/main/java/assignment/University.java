//package assignment;

import assignment.*;

import java.util.*;

void main(String[] args) {
    seqColl();
    seqSet();
    seqMap();

    EngineeringFaculty engFaculty = new EngineeringFaculty();
    SoftwareEngineeringDept swEngDept = new SoftwareEngineeringDept();
    LecturerRecord mikeBloggs = new LecturerRecord(
            "Mike Bloggs", 44, engFaculty, swEngDept
    );
    recordPatterns(mikeBloggs);

    BusinessFaculty businessFaculty = new BusinessFaculty();
    AccountingDept accountingDept = new AccountingDept();
    LecturerRecord alanAustin = new LecturerRecord(
            "Alan Austin", 64, businessFaculty, accountingDept
    );
    recordPatterns(alanAustin);

    HumanitiesFaculty humanitiesFaculty = new HumanitiesFaculty();
    SocialCareDept socialCareDept = new SocialCareDept();
    LecturerRecord lisaBloggs = new LecturerRecord(
            "Lisa Bloggs", 65, humanitiesFaculty, socialCareDept
    );
    recordPatterns(lisaBloggs);
}

private void seqColl() {
    SequencedCollection<LecturerRecord> lecturerRecords = new ArrayList<>();
    LecturerRecord jane = new LecturerRecord(
            "Jane Bloggs",
            24,
            new EngineeringFaculty(),
            new SoftwareEngineeringDept()
    );
    lecturerRecords.addFirst(jane);
    LecturerRecord anne = new LecturerRecord(
            "Dr. Anne Bloggs",
            35,
            new EngineeringFaculty(),
            new SoftwareEngineeringDept()
    );
    lecturerRecords.addFirst(anne);
    LecturerRecord joe = new LecturerRecord(
            "Joe Bloggs PhD",
            54,
            new EngineeringFaculty(),
            new SoftwareEngineeringDept()
    );
    lecturerRecords.addLast(joe);

    System.out.println(lecturerRecords);

    System.out.format("getFirst() : %s\n", lecturerRecords.getFirst());
    System.out.format("getLast() : %s\n", lecturerRecords.getLast());

    System.out.format("removeLast() : %s\n", lecturerRecords.removeLast());
    System.out.println(lecturerRecords);
    System.out.format("getFirst() : %s\n", lecturerRecords.getFirst());
    System.out.format("getLast() : %s\n", lecturerRecords.getLast());

    System.out.format("removeLast() : %s\n", lecturerRecords.removeLast());
    System.out.println(lecturerRecords);

    System.out.println("List in normal order");
    for (LecturerRecord lr : lecturerRecords) {
        System.out.println(lr);
    }

    System.out.println("List in reversed order");
    for (LecturerRecord lr : lecturerRecords.reversed()) {
        System.out.println(lr);
    }
}

private void seqSet() {
    SequencedSet<LecturerRecord> lecturerRecords = new LinkedHashSet<>();
    LecturerRecord jane = new LecturerRecord(
            "Jane Austin",
            24,
            new BusinessFaculty(),
            new AccountingDept()
    );
    lecturerRecords.addFirst(jane);
    lecturerRecords.addFirst(jane);
    lecturerRecords.addFirst(jane);
    LecturerRecord crarlotte = new LecturerRecord(
            "Dr. Charlotte Bronte",
            35,
            new BusinessFaculty(),
            new AccountingDept()
    );
    lecturerRecords.addFirst(crarlotte);
    lecturerRecords.addLast(jane);
    LecturerRecord anne = new LecturerRecord(
            "Anne Bronte PhD",
            54,
            new BusinessFaculty(),
            new AccountingDept()
    );
    lecturerRecords.addLast(anne);

    System.out.println(lecturerRecords);

    System.out.format("getFirst() : %s\n", lecturerRecords.getFirst());
    System.out.format("getLast() : %s\n", lecturerRecords.getLast());

    System.out.format("removeFirst() : %s\n", lecturerRecords.removeFirst());
    System.out.println(lecturerRecords);

    System.out.println("Set in normal order");
    for (LecturerRecord lr : lecturerRecords) {
        System.out.println(lr);
    }

    System.out.println("Set in reversed order");
    for (LecturerRecord lr : lecturerRecords.reversed()) {
        System.out.println(lr);
    }
}

private void seqMap() {
    SequencedMap<LecturerRecord, String> lecturerRecords = new LinkedHashMap<>();
    LecturerRecord king = new LecturerRecord(
            "King Lear",
            88,
            new HumanitiesFaculty(),
            new SocialCareDept()
    );
    LecturerRecord goneril = new LecturerRecord(
            "Goneril",
            55,
            new HumanitiesFaculty(),
            new SocialCareDept()
    );
    LecturerRecord regan = new LecturerRecord(
            "Regan",
            50,
            new HumanitiesFaculty(),
            new SocialCareDept()
    );
    LecturerRecord cordelia = new LecturerRecord(
            "Cordelia",
            45,
            new HumanitiesFaculty(),
            new SocialCareDept()
    );

    lecturerRecords.putFirst(goneril, "Eldest");
    lecturerRecords.putFirst(regan, "Middle");
    lecturerRecords.putLast(cordelia, "Youngest");
    lecturerRecords.putLast(king, "Father");

    System.out.println(lecturerRecords);

    System.out.format("firstEntry() : %s\n", lecturerRecords.firstEntry());
    System.out.format("lastEntry() : %s\n", lecturerRecords.lastEntry());

    System.out.format("pollLastEntry() : %s\n", lecturerRecords.pollLastEntry());
    System.out.println(lecturerRecords);

    System.out.println("Map in normal order");
    for (Map.Entry<LecturerRecord, String> entryMap : lecturerRecords.entrySet()) {
        System.out.format("%s = %s \n", entryMap.getKey(), entryMap.getValue());
    }

    System.out.println("Map in reversed order");
    for (Map.Entry<LecturerRecord, String> entryMap : lecturerRecords.reversed().entrySet()) {
        System.out.format("%s = %s \n", entryMap.getKey(), entryMap.getValue());
    }
}

private void recordPatterns(Object obj) {
    // Calculate the staff that either should be retired or are reaching retirement age
    // within the next year i.e. age >= 64 (retirement age is 65).
    System.out.println(
            switch (obj) {
                case LecturerRecord(String name, Integer age, Faculty faculty, Department dept)
                        when age >= 64 -> {
                    String output = """
                            	Name: %s,
                            	Age: %s,
                            	Faculty: %s,
                            	Department: %s
                            """.formatted(name, age, faculty, dept);
                    yield output;
                }
                case null, default -> "";
            }
    );
}


