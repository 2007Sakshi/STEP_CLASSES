class EventTicket {

    double amountPaid;

    public void pay(double amount) {

        amountPaid += amount;

        System.out.println(
            "Paid: " + amount
        );
    }


    public void pay(double amount, String mode) {

        System.out.println(
            "Payment mode: " + mode
        );

        pay(amount);
    }
}


public class OverloadingDemo {

    public static void main(String[] args) {

        EventTicket t =
            new EventTicket();

        t.pay(200);

        t.pay(300, "UPI");

        System.out.println(
            "Total Paid: " + t.amountPaid
        );
    }
}