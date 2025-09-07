public class Book extends LibraryItem {

    // Fields
    private String isbn;

    // Constants
    private static final double DAILY_LATE_FEE = 0.25;
    private static final int BASE_LOAN_PERIOD = 21;
    private static final double MAX_FINE = 20.0;

    // Getter
    public String getISBN() {
        return isbn;
    }

    // Setter
    public void setISBN(String isbn) {
        if (isbn == null || isbn.trim().isEmpty())
            throw new IllegalArgumentException("ISBN must not be null or empty");
        this.isbn = isbn.trim();
    }

    // Constructor
    public Book(String title, String isbn) {
        super(title);
        setISBN(isbn);
    }

    // Methods
    @Override
    public double getDailyLateFee() {
        return DAILY_LATE_FEE;
    }

    @Override
    public int getBaseLoanPeriod() {
        return BASE_LOAN_PERIOD;
    }

    @Override
    public double getMaximumFine() {
        return MAX_FINE;
    }

    @Override
    public String toString() {
        return getTitle() + " (Book, ISBN: " + getISBN() + ")";
    }
}