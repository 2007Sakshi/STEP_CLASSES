
import java.util.*;

class Student {
    String rollNumber;
    String name;

    Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;

        Student other = (Student) obj;
        return rollNumber.equals(other.rollNumber);
    }

    public int hashCode() {
        return rollNumber.hashCode();
    }
}

public class club {
    public static void main(String[] args) {
        Set<Student> members = new HashSet<>();

        add(members, new Student("21CS01", "Asha"));
        add(members, new Student("21CS01", "Asha"));
        add(members, new Student("21CS02", "Ravi"));

        System.out.println("member count " + members.size());

        System.out.println("contains: " +
            members.contains(new Student("21CS02", "Ravi")));

        System.out.println("contains: " +
            members.contains(new Student("21CS03", "Priya")));
    }

    static void add(Set<Student> members, Student student) {
        if (members.add(student)) {
            System.out.println("Added");
        } else {
            System.out.println("duplicate rejected");
        }
    }
}
