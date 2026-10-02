import java.util.*;

abstract class Question {
    String question;
    int marks;

    Question(String question, int marks) {
        this.question = question;
        this.marks = marks;
    }

    abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {

    String correctAnswer;

    MultipleChoiceQuestion(String question, String correctAnswer, int marks) {
        super(question, marks);
        this.correctAnswer = correctAnswer;
    }

    boolean evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}

class TrueFalseQuestion extends Question {

    boolean correctAnswer;

    TrueFalseQuestion(String question, boolean correctAnswer, int marks) {
        super(question, marks);
        this.correctAnswer = correctAnswer;
    }

    boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Examination {
    String name;
    List<Question> questions = new ArrayList<>();

    Examination(String name) {
        this.name = name;
    }

    void addQuestion(Question q) {
        questions.add(q);
    }
}

class Attempt {

    private Student student;
    private Examination exam;
    private Map<Integer, String> answers = new HashMap<>();
    private boolean submitted = false;

    Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;

        System.out.println(exam.name +
                " started by " + student.name + ".");
    }

    void answerQuestion(int number, String answer) {

        if (submitted) {
            System.out.println(
                    "Cannot change answers for a submitted examination.");
            return;
        }

        answers.put(number, answer);
        System.out.println(
                "Answer recorded for Question " + number + ".");
    }

    void submit() {

        if (submitted) {
            return;
        }

        submitted = true;

        System.out.println(exam.name +
                " submitted by " + student.name + ".");

        int total = 0;
        int obtained = 0;

        for (int i = 0; i < exam.questions.size(); i++) {

            Question q = exam.questions.get(i);
            String answer = answers.get(i);

            total += q.marks;

            if (answer != null && q.evaluate(answer)) {
                obtained += q.marks;

                System.out.println("Result: Question " +
                        (i + 1) + ": Correct (" +
                        q.marks + " points)");
            } else {
                System.out.println("Result: Question " +
                        (i + 1) + ": Incorrect (0 points)");
            }
        }

        System.out.println("Total score: " +
                obtained + "/" + total);
    }
}

public class exam {
    public static void main(String[] args) {

        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(
                new MultipleChoiceQuestion(
                        "Which is an OOP concept?",
                        "C",
                        5));

        exam.addQuestion(
                new TrueFalseQuestion(
                        "Java supports OOP.",
                        false,
                        5));

        Attempt attempt = new Attempt(student, exam);

        attempt.answerQuestion(1, "C");
        attempt.answerQuestion(2, "True");

        attempt.submit();

        attempt.answerQuestion(1, "A");
    }
}