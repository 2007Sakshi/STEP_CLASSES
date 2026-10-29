
import java.util.*;

abstract class Account {
    String id;
    int balance;

    Account(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    abstract boolean withdraw(int amount);
}

class Savings extends Account {
    Savings(String id, int balance) {
        super(id, balance);
    }

    boolean withdraw(int amount) {
        if (balance - amount < 1000) {
            System.out.println(
                id + " rejected: minimum balance 1000"
            );
            return false;
        }

        balance -= amount;
        System.out.println(id + " balance " + balance);
        return true;
    }
}

class Current extends Account {
    Current(String id, int balance) {
        super(id, balance);
    }

    boolean withdraw(int amount) {
        if (balance - amount < -5000) {
            System.out.println(
                id + " rejected: overdraft limit 5000"
            );
            return false;
        }

        balance -= amount;
        System.out.println(id + " balance " + balance);
        return true;
    }
}

public class bank {
    public static void main(String[] args) {
        Map<String, Account> accounts = new HashMap<>();

        accounts.put("S1", new Savings("S1", 5000));
        accounts.put("C1", new Current("C1", 2000));

        withdraw(accounts, "S1", 4500);
        withdraw(accounts, "C1", 6000);
        withdraw(accounts, "S1", 3000);
        withdraw(accounts, "C1", 2000);
    }

    static void withdraw(Map<String, Account> accounts,
                         String id, int amount) {
        Account account = accounts.get(id);

        if (account == null) {
            System.out.println("Account not found");
        } else {
            account.withdraw(amount);
        }
    }
}
