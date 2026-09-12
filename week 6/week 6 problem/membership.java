class GymMember {

    private static int membersEnrolled = 0;

    public final String membershipNumber;

    protected int monthlyFee;
    protected int feesPaid;

    GymMember(int monthlyFee) {

        this.monthlyFee = monthlyFee;
        this.feesPaid = 0;

        membersEnrolled++;

        membershipNumber =
                "GYM-" + (2000 + membersEnrolled);
    }

    public void payFee(int amount) {
        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        feesPaid += amount;

        System.out.println(
            "Paid ₹" + amount + " using " + mode
        );
    }

    public static boolean isValidReferralCode(String code) {

        if (code == null || code.length() != 4)
            return false;

        if (code.charAt(0) != 'G')
            return false;

        if (!Character.isDigit(code.charAt(1)))
            return false;

        if (!Character.isDigit(code.charAt(2)))
            return false;

        if (!Character.isUpperCase(code.charAt(3)))
            return false;

        return true;
    }
}

class GroupClassMember extends GymMember {

    GroupClassMember(int monthlyFee) {
        super(monthlyFee);
    }
}

public class membership{

    static void processWeeklyCheckIn(GymMember[] members) {

        int processed = 0;
        int group = 0;
        int individual = 0;

        for (GymMember member : members) {

            if (member == null)
                continue;

            processed++;

            if (member instanceof GroupClassMember)
                group++;
            else
                individual++;
        }

        System.out.println("Processed: " + processed);
        System.out.println("Group Members: " + group);
        System.out.println("Individual Members: " + individual);
    }

    public static void main(String[] args) {

        GymMember m1 = new GymMember(1000);
        GymMember m2 = new GroupClassMember(1500);
        GymMember m3 = new GymMember(2000);
        GymMember m4 = new GroupClassMember(1800);

        m1.payFee(1000);
        m2.payFee(1500, "UPI");

        System.out.println(m1.membershipNumber);
        System.out.println(m2.membershipNumber);

        System.out.println(
            "GYM12A: "
            + GymMember.isValidReferralCode("GYM12A")
        );

        System.out.println(
            "G12A: "
            + GymMember.isValidReferralCode("G12A")
        );

        GymMember[] members = {
            m1, m2, m3, m4, null
        };

        processWeeklyCheckIn(members);
    }
}