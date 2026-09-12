class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;


    // Constructor
    public LoanReceipt(String memberId, String[] bookIds) {

        this.memberId = memberId;

        // Defensive copy
        this.bookIds = bookIds.clone();
    }


    // Getter with defensive copy
    public String[] getBookIds() {

        return bookIds.clone();
    }


    // Wither method
    public LoanReceipt withCorrectedBookId(
            int index, String newId) {

        String[] newBookIds = bookIds.clone();

        newBookIds[index] = newId;

        return new LoanReceipt(
            memberId,
            newBookIds
        );
    }
}


// Reference-only receipt
class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;


    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }
}


// Circulation ledger
public class Problem5 {

    private static String branchCode;


    // Static block
    static {
        branchCode = "BRANCH-001";
    }


    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;


        // Single pass
        for (LoanReceipt receipt : receipts) {

            // Handle null
            if (receipt == null) {
                nullSkipped++;
                continue;
            }


            processed++;


            // Check object type
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            }
            else {
                regular++;
            }
        }


        return processed + " processed | "
             + nullSkipped + " null skipped | "
             + referenceOnly + " reference-only | "
             + regular + " regular";
    }


    public static void main(String[] args) {

        // Original receipt
        LoanReceipt r = new LoanReceipt(
            "LIB-8841",
            new String[]{"BK-100", "BK-101"}
        );


        // Test defensive copying
        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(
            r.getBookIds()[0]
        );


        // Corrected receipt
        LoanReceipt corrected =
            r.withCorrectedBookId(
                1,
                "BK-102"
            );


        System.out.println(
            r.getBookIds()[0] + ", "
            + r.getBookIds()[1]
        );

        System.out.println(
            corrected.getBookIds()[0] + ", "
            + corrected.getBookIds()[1]
        );


        // Nightly processing
        LoanReceipt[] receipts = {

            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"
            ),

            null,

            new LoanReceipt(
                "LIB-002",
                new String[]{"BK-201"}
            )
        };


        System.out.println(
            processNightlyCirculation(receipts)
        );
    }
}