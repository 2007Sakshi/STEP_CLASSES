class GymMember {

    private int[] lateFeeHistory = new int[10];
    private int feeCount = 0;

    protected void chargeLateFee(int amount) {

        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }
    }

    public int[] getLateFeeHistory() {

        int[] result = new int[feeCount];

        for (int i = 0; i < feeCount; i++) {
            result[i] = lateFeeHistory[i];
        }

        return result;
    }

    public int getTotalLateFees() {

        int total = 0;

        for (int i = 0; i < feeCount; i++) {
            total += lateFeeHistory[i];
        }

        return total;
    }
}

class PremiumMember extends GymMember {

    @Override
    protected void chargeLateFee(int amount) {

        int discountedAmount = amount / 2;

        super.chargeLateFee(discountedAmount);
    }
}

public class discount {

    public static void main(String[] args) {

        GymMember normal = new GymMember();
        PremiumMember premium = new PremiumMember();

        normal.chargeLateFee(100);
        normal.chargeLateFee(200);

        premium.chargeLateFee(100);
        premium.chargeLateFee(200);

        System.out.println("Normal Total: "
                + normal.getTotalLateFees());

        System.out.println("Premium Total: "
                + premium.getTotalLateFees());

        System.out.println("Premium Late Fee History:");

        for (int fee : premium.getLateFeeHistory()) {
            System.out.println(fee);
        }
    }
}