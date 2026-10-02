interface WashType {
    int getDuration();
    double getCharge();
    String getName();
}

class QuickWash implements WashType {
    public int getDuration() { return 30; }
    public double getCharge() { return 20; }
    public String getName() { return "Quick"; }
}

class NormalWash implements WashType {
    public int getDuration() { return 45; }
    public double getCharge() { return 30; }
    public String getName() { return "Normal"; }
}

class HeavyWash implements WashType {
    public int getDuration() { return 60; }
    public double getCharge() { return 45; }
    public String getName() { return "Heavy"; }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class WashingMachine {
    private String id;
    private boolean free = true;
    private WashCycle cycle;

    WashingMachine(String id) {
        this.id = id;
    }

    public boolean isFree() {
        return free;
    }

    public void startWash(Student student, WashType type) {
        if (!free) {
            System.out.println("Machine " + id + " is currently busy.");
            return;
        }

        cycle = new WashCycle(student, this, type);
        free = false;

        System.out.printf("%s wash started on %s for %s (%d min).%n",
                type.getName(), id, student.name, type.getDuration());
        System.out.printf("Charge: ₹%.2f%n", type.getCharge());
    }

    public void completeWash() {
        if (!free) {
            System.out.println(id + " cycle completed.");
            free = true;
            cycle = null;
            System.out.println(id + " is now free.");
        }
    }

    public String getId() {
        return id;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType type;

    WashCycle(Student student, WashingMachine machine, WashType type) {
        this.student = student;
        this.machine = machine;
        this.type = type;
    }
}

public class student{
    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());

        m2.startWash(ravi, new HeavyWash());

        m1.completeWash();

        m1.startWash(neha, new NormalWash());
    }
}