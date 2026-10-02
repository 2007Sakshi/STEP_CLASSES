interface LeavePolicy {
    boolean canTakeLeave(int days);
}

class FullTimePolicy implements LeavePolicy {
    public boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimePolicy implements LeavePolicy {
    public boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class ContractorPolicy implements LeavePolicy {
    public boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

class Employee {
    String name;
    LeavePolicy policy;

    Employee(String name, LeavePolicy policy) {
        this.name = name;
        this.policy = policy;
    }
}

class LeaveRequest {
    private Employee employee;
    private String startDate;
    private String endDate;
    private int days;
    private String status;

    LeaveRequest(Employee employee, String startDate,
                 String endDate, int days) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.status = "Pending";

        System.out.println("Leave request submitted for "
                + employee.name + " (" + startDate + "-" + endDate + ").");
        System.out.println("Status: Pending");
    }

    void approve() {
        if (!status.equals("Pending")) {
            System.out.println("Cannot approve. Status is " + status);
            return;
        }

        if (!employee.policy.canTakeLeave(days)) {
            System.out.println("Leave policy does not allow this request.");
            return;
        }

        status = "Approved";

        System.out.println(employee.name +
                "'s leave request (" + startDate + "-" + endDate +
                ") approved.");
        System.out.println("Status: Approved");
    }

    void reject() {
        if (!status.equals("Pending")) {
            System.out.println("Cannot reject. Status is " + status);
            return;
        }

        status = "Rejected";

        System.out.println(employee.name +
                "'s leave request (" + startDate + "-" + endDate +
                ") rejected.");
        System.out.println("Status: Rejected");
    }

    void changeToPending() {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from "
                    + status + " to Pending.");
        }
    }
}

public class leave{
    public static void main(String[] args) {

        Employee john =
                new Employee("John", new FullTimePolicy());

        Employee jane =
                new Employee("Jane", new PartTimePolicy());

        LeaveRequest r1 =
                new LeaveRequest(john, "Jan 1", "Jan 5", 5);

        r1.approve();

        LeaveRequest r2 =
                new LeaveRequest(jane, "Feb 10", "Feb 11", 2);

        r2.reject();

        r1.changeToPending();
    }
}