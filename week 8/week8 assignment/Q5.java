import java.util.*;

interface NotificationChannel {
    void send(Student student, String message);
}

class EmailChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[Email → " +
                student.getName() + "] " + message);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[SMS → " +
                student.getName() + "] " + message);
    }
}

class AppChannel implements NotificationChannel {
    public void send(Student student, String message) {
        System.out.println("[App → " +
                student.getName() + "] " + message);
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> channels =
            new ArrayList<>();

    Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void receive(String message) {
        for (NotificationChannel channel : channels)
            channel.send(this, message);
    }
}

class Notice {
    private String title;
    private Set<String> departments;

    Notice(String title, Set<String> departments) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Notice title is required.");

        if (departments == null || departments.isEmpty())
            throw new IllegalArgumentException(
                    "At least one target department is required.");

        this.title = title;
        this.departments = departments;
    }

    public String getTitle() {
        return title;
    }

    public boolean targets(String department) {
        return departments.contains(department);
    }

    public String getDepartments() {
        return String.join(", ", departments);
    }
}

class NoticeBoard {
    private List<Student> students =
            new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {

        System.out.println("Notice '" +
                notice.getTitle() +
                "' posted to " +
                notice.getDepartments() + ".");

        for (Student student : students) {
            if (notice.targets(student.getDepartment())) {
                student.receive(notice.getTitle());
            }
        }
    }
}

public class Q5 {
    public static void main(String[] args) {

        Student asha = new Student("Asha", "CSE");
        Student ravi = new Student("Ravi", "ECE");

        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        ravi.addChannel(new SmsChannel());

        NoticeBoard board = new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        Set<String> cse =
                new HashSet<>(Arrays.asList("CSE"));

        Set<String> cseEce =
                new HashSet<>(Arrays.asList("CSE", "ECE"));

        Notice n1 =
                new Notice("Lab Closed Tomorrow", cse);

        board.postNotice(n1);

        Notice n2 =
                new Notice("Fee Deadline Extended", cseEce);

        board.postNotice(n2);

        try {
            Notice n3 =
                    new Notice("Sports Day",
                            new HashSet<>());
            board.postNotice(n3);
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot post notice: " +
                    e.getMessage());
        }
    }
}