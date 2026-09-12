public class book {

    private int copiesTotal;
    private int copiesAvailable;

    // Constructor
    Book(int copiesTotal) {
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    // Check out one book
    void checkOut() {

        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    // Check in one book
    void checkIn() {

        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    // Getter
    int getCopiesAvailable() {
        return copiesAvailable;
    }


    public static void main(String[] args) {

        Book b = new Book(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();

        // 4th checkout
        b.checkOut();

        System.out.println(
            b.getCopiesAvailable()
        );

        b.checkIn();
        b.checkIn();
        b.checkIn();

        // 4th check-in
        b.checkIn();

        System.out.println(
            b.getCopiesAvailable()
        );
    }
}