public abstract class LibraryItem {

    // Fields
    private String title;
    private Loan currentLoan;

    // Getter
    public String getTitle() {
        return title;
    }

    public Loan getCurrentLoan() {
        return currentLoan;
    }

    // Setter
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be null or empty");
        }
        this.title = title.trim();
    }

    // Constructor
    public LibraryItem(String title) {
        setTitle(title);
        this.currentLoan = null;
    }

    // Methods
    public void assignLoan(Loan loan) {
        if (loan == null)
            throw new IllegalArgumentException("Loan cannot be null");
        if (this.currentLoan != null)
            throw new ItemUnavailableException("Item is already on loan");
        // ensure the loan references this item
        if (loan.getLibraryItem() != this)
            throw new IllegalArgumentException("Loan does not reference this item");
        this.currentLoan = loan;
    }

    public void removeLoan() {
        if (this.currentLoan == null)
            throw new IllegalArgumentException("No current loan to remove");
        this.currentLoan = null;
    }

    public boolean isOnLoan() {
        return this.currentLoan != null;
    }

    public boolean isAvailableForLoan() {
        return !isOnLoan();
    }

    // Returns the specific daily late fee for the library item (use the table
    // provided).
    // Use the table provided in the assignment documentation to generate the values
    // for the daily late fee.
    public abstract double getDailyLateFee();

    // Returns the base loan period for the item (before any additional time due to
    // user type is considered).
    // Use the table provided in the assignment documentation to generate the values
    // for the base loan period.
    public abstract int getBaseLoanPeriod();

    // Returns the maximum possible fine for an overdue loan on this item.
    // Use the table provided in the assignment documentation to generate the values
    // for the maximum fine.
    public abstract double getMaximumFine();
}