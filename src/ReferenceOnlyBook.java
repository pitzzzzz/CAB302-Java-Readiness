public class ReferenceOnlyBook extends Book {

    // Constants
    private static final double DAILY_LATE_FEE = 0.0;
    private static final int BASE_LOAN_PERIOD = 0;
    private static final double MAX_FINE = 0.0;

    // Constructor
    public ReferenceOnlyBook(String title, String isbn) {
        super(title, isbn);
    }

    // Methods
    @Override
    public void assignLoan(Loan loan) {
        throw new ItemNotLoanableException("Reference-only book cannot be loaned");
    }

    @Override
    public boolean isAvailableForLoan() {
        return false;
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
        return getTitle() + " (Reference Book, ISBN: " + getISBN() + ")";
    }
}