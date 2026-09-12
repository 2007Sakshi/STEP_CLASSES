class EventTicket {

    protected double amountPaid;

    public void applyLateRegistrationFee(double amount) {

        amountPaid -= amount;
    }
}


class WorkshopTicket extends EventTicket {

    @Override
    public void applyLateRegistrationFee(double amount) {

        super.applyLateRegistrationFee(amount * 2);
    }
}


public class OverridingDemo {

    public static void main(String[] args) {

        WorkshopTicket w =
            new WorkshopTicket();

        w.amountPaid = 1200;

        w.applyLateRegistrationFee(100);

        System.out.println(w.amountPaid);
    }
}