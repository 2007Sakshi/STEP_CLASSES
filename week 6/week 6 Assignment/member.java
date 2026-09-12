class GymMember {

    protected String memberId;
    protected int monthlyFee;

    private int[] lateFeeHistory;
    private int feeCount;


    public GymMember(
            String memberId,
            int monthlyFee) {

        if (memberId == null ||
            memberId.trim().length() < 4) {

            throw new IllegalArgumentException(
                "Invalid member ID"
            );
        }

        if (monthlyFee <= 0) {

            throw new IllegalArgumentException(
                "Invalid monthly fee"
            );
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;

        lateFeeHistory = new int[10];
        feeCount = 0;
    }


    protected void chargeLateFee(int amount) {

        if (feeCount < 10) {

            lateFeeHistory[feeCount] = amount;

            feeCount++;
        }
    }


    public int[] getLateFeeHistory() {

        int[] copy =
            new int[feeCount];

        for (int i = 0; i < feeCount; i++) {

            copy[i] = lateFeeHistory[i];
        }

        return copy;
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

    private String trainerName;


    public PremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }


    @Override
    protected void chargeLateFee(int amount) {

        super.chargeLateFee(amount / 2);
    }
}


public class member{

    public static void main(String[] args) {

        PremiumMember p =
            new PremiumMember(
                "MEM5",
                2000,
                "Coach Riya"
            );


        // 200 becomes 100
        p.chargeLateFee(200);

        System.out.println(
            p.getTotalLateFees()
        );


        // Defensive copy test
        int[] history =
            p.getLateFeeHistory();

        history[0] = 999;


        System.out.println(
            p.getLateFeeHistory()[0]
        );


        // Add another fee
        p.chargeLateFee(300);

        System.out.println(
            p.getTotalLateFees()
        );
    }
}