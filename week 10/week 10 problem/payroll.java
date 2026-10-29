
abstract class Staff {
    String name;

    Staff(String name) {
        this.name = name;
    }

    abstract int calculatePay();
    abstract String getType();

    public String toString() {
        return "Payslip[name=" + name
            + ", type=" + getType()
            + ", pay=" + calculatePay() + "]";
    }
}

class FullTime extends Staff {
    int salary;

    FullTime(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    int calculatePay() {
        return salary;
    }

    String getType() {
        return "FullTime";
    }
}

class PartTime extends Staff {
    int hours, rate;

    PartTime(String name, int hours, int rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    int calculatePay() {
        return hours * rate;
    }

    String getType() {
        return "PartTime";
    }
}

class Intern extends Staff {
    int stipend;

    Intern(String name, int stipend) {
        super(name);
        this.stipend = stipend;
    }

    int calculatePay() {
        return stipend;
    }

    String getType() {
        return "Intern";
    }
}

public class payroll {
    public static void main(String[] args) {
        Staff[] staff = {
            new FullTime("Asha", 50000),
            new PartTime("Ravi", 80, 300),
            new Intern("Neha", 15000)
        };

        int total = 0;
        int top = 0;

        for (int i = 0; i < staff.length; i++) {
            System.out.println(staff[i]);

            total += staff[i].calculatePay();

            if (staff[i].calculatePay()
                    > staff[top].calculatePay()) {
                top = i;
            }
        }

        System.out.println("Total " + total);
        System.out.println("top earner " + staff[top].name);
    }
}
