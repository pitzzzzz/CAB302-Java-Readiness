public class Book extends LibraryItem {

    private String isbn;
    private static final double DAILY_LATE_FEE = 0.25;
    private static final int BASE_LOAN_PERIOD = 21;
    private static final double MAX_FINE = 20.0;

    public Book(String title, String isbn) {
        super(title);
        setISBN(isbn);
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        if (isbn == null || isbn.trim().isEmpty())
            throw new IllegalArgumentException("ISBN must not be null or empty");
        this.isbn = isbn.trim();
    }

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