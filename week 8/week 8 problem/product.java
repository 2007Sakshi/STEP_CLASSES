import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
    String getName();
}

class CreditCardPayment implements PaymentMethod {

    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card payment...");
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {

    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment...");
        return false;
    }

    public String getName() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {

    public boolean processPayment(double amount) {
        System.out.println("Processing Bank Transfer...");
        return true;
    }

    public String getName() {
        return "Bank Transfer";
    }
}

class Product {

    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class OrderItem {

    Product product;
    int quantity;

    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotal() {
        return product.price * quantity;
    }
}

class Customer {

    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Order {

    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private String status = "Pending";

    Order(Customer customer) {
        this.customer = customer;

        System.out.println(
                "Order created for " +
                customer.name + ".");
    }

    void addProduct(Product product, int quantity) {

        if (quantity <= 0) {
            return;
        }

        items.add(new OrderItem(product, quantity));
    }

    double getTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    void pay(PaymentMethod method) {

        if (items.isEmpty()) {
            System.out.println(
                    "Cannot process payment for an empty order.");
            return;
        }

        System.out.println(
                "Payment initiated via " +
                method.getName() +
                " for Order " +
                customer.name + ".");

        boolean success =
                method.processPayment(getTotal());

        if (success) {

            status = "Paid";

            System.out.println(
                    "Payment for Order " +
                    customer.name +
                    " successful.");

        } else {

            System.out.println(
                    "Payment for Order " +
                    customer.name +
                    " failed.");
        }

        System.out.println(
                "Order status: " + status);
    }
}

public class product{

    public static void main(String[] args) {

        Customer x = new Customer("Customer X");

        Product a = new Product("Product A", 100);
        Product b = new Product("Product B", 200);

        Order orderX = new Order(x);

        orderX.addProduct(a, 2);
        orderX.addProduct(b, 1);

        orderX.pay(new CreditCardPayment());

        Customer y = new Customer("Customer Y");

        Order orderY = new Order(y);

        orderY.pay(new CreditCardPayment());

        Customer z = new Customer("Customer Z");

        Product c = new Product("Product C", 300);

        Order orderZ = new Order(z);

        orderZ.addProduct(c, 1);

        orderZ.pay(new PayPalPayment());
    }
}