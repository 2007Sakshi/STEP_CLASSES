class GymMember {

    private static int membersEnrolled = 0;

    public final String membershipNumber;

    protected int monthlyFee;

    private int feesPaid;


    public GymMember(int monthlyFee) {

        if (monthlyFee <= 0) {

            throw new IllegalArgumentException(
                "Monthly fee must be positive"
            );
        }

        this.monthlyFee = monthlyFee;

        membersEnrolled++;

        membershipNumber =
            "GYM-" + (2000 + membersEnrolled);
    }


    public void payFee(int amount) {

        if (amount > 0) {

            feesPaid += amount;
        }
    }


    public void payFee(
            int amount,
            String mode) {

        System.out.println(
            "Payment Mode: " + mode
        );

        payFee(amount);
    }


    public int getFeesPaid() {
        return feesPaid;
    }


    public static boolean isValidReferralCode(
            String code) {

        // Must contain exactly 4 characters
        if (code == null ||
            code.length() != 4) {

            return false;
        }


        // First character must be G
        if (code.charAt(0) != 'G') {
            return false;
        }


        // Second and third must be digits
        if (!Character.isDigit(
                code.charAt(1))) {

            return false;
        }


        if (!Character.isDigit(
                code.charAt(2))) {

            return false;
        }


        // Fourth must be uppercase letter
        if (!Character.isUpperCase(
                code.charAt(3))) {

            return false;
        }


        return true;
    }


    public static int getMembersEnrolled() {
        return membersEnrolled;
    }
}


class GroupClassMember extends GymMember {

    private String className;


    public GroupClassMember(
            int monthlyFee,
            String className) {

        super(monthlyFee);

        this.className = className;
    }
}


public class weeklycheck{


    static String processWeeklyCheckIn(
            GymMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;


        for (GymMember member : members) {

            // Skip null
            if (member == null) {

                nullSkipped++;

                continue;
            }


            processed++;


            if (member instanceof GroupClassMember) {

                group++;

            } else {

                individual++;
            }
        }


        return processed
             + " processed | "
             + nullSkipped
             + " null skipped | "
             + group
             + " group | "
             + individual
             + " individual";
    }


    public static void main(String[] args) {


        // Membership number
        GymMember m1 =
            new GymMember(1000);


        System.out.println(
            m1.membershipNumber
        );


        System.out.println(
            GymMember.getMembersEnrolled()
        );


        // Referral codes
        System.out.println(
            GymMember.isValidReferralCode(
                "G45B"
            )
        );


        System.out.println(
            GymMember.isValidReferralCode(
                "G4B"
            )
        );


        System.out.println(
            GymMember.isValidReferralCode(
                "X45B"
            )
        );


        // Overloaded payment methods
        m1.payFee(500);

        m1.payFee(
            500,
            "UPI"
        );


        System.out.println(
            m1.getFeesPaid()
        );


        // Weekly check-in
        GroupClassMember group =
            new GroupClassMember(
                1500,
                "Zumba"
            );


        GymMember individual =
            new GymMember(1000);


        GymMember[] members = {
            group,
            null,
            individual
        };


        System.out.println(
            processWeeklyCheckIn(members)
        );
    }
}