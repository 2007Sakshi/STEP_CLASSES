import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    protected String title;
    protected int maxMarks;
    protected LocalDate dueDate;

    Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    abstract double applyPenalty(double marks, long lateDays);
}

class CodingAssignment extends Assignment {
    CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double applyPenalty(double marks, long lateDays) {
        return Math.max(0, marks * (1 - 0.10 * lateDays));
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double applyPenalty(double marks, long lateDays) {
        return Math.max(0, marks * (1 - 0.20 * lateDays));
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private String status = "Submitted";

    Submission(Student student, Assignment assignment, LocalDate date) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = date;

        long lateDays = Math.max(0,
                ChronoUnit.DAYS.between(assignment.dueDate, date));

        if (lateDays == 0)
            System.out.println(student.name + "'s submission for '" +
                    assignment.title + "' received (on time).");
        else
            System.out.println(student.name + "'s submission for '" +
                    assignment.title + "' received (" +
                    lateDays + " days late).");

        System.out.println("Status: " + status);
    }

    public void grade(double marks) {
        if (!status.equals("Submitted")) {
            System.out.println("Already graded.");
            return;
        }

        long lateDays = Math.max(0,
                ChronoUnit.DAYS.between(assignment.dueDate, submissionDate));

        double finalMarks = assignment.applyPenalty(marks, lateDays);

        System.out.printf("%s graded: %.0f/%d. Status: Graded.%n",
                student.name, finalMarks, assignment.maxMarks);

        status = "Graded";
    }

    public void resubmit() {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" +
                    assignment.title + "' has already been graded.");
        }
    }
}

public class assignment {
    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
                "Linked List Lab", 50,
                LocalDate.of(2026, 3, 10));

        Assignment written = new WrittenAssignment(
                "Design Essay", 50,
                LocalDate.of(2026, 3, 12));

        Submission s1 = new Submission(
                asha, coding, LocalDate.of(2026, 3, 10));

        Submission s2 = new Submission(
                ravi, written, LocalDate.of(2026, 3, 14));

        s1.grade(45);
        s2.grade(40);

        s1.resubmit();
    }
}