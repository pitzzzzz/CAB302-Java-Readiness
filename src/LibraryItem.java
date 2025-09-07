import java.text.NumberFormat;
import java.util.Locale;

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
        validateTitle(title);
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
        if (loan.getLibraryItem() != this)
            throw new IllegalArgumentException("Loan does not reference this item");
        this.currentLoan = loan;
    }

    public void removeLoan() {
        if (this.currentLoan == null)
            throw new IllegalArgumentException("No current loan to remove");
        this.currentLoan = null;
    }

    // overloaded method
    public void removeLoan(Loan loan) {
        if (loan == null)
            throw new IllegalArgumentException("Loan cannot be null");
        if (this.currentLoan == null || this.currentLoan != loan)
            throw new IllegalArgumentException("Loan not found for this item");
        this.currentLoan = null;
    }

    public boolean isOnLoan() {
        return this.currentLoan != null;
    }

    public boolean isAvailableForLoan() {
        return !isOnLoan();
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title must not be null or empty");
    }

    public abstract double getDailyLateFee();

    public abstract int getBaseLoanPeriod();

    public abstract double getMaximumFine();

    // Helper methods (for formatted output)
    public String getDailyLateFeeFormatted() {
        return formatCurrency(getDailyLateFee());
    }

    public String getMaximumFineFormatted() {
        return formatCurrency(getMaximumFine());
    }

    private static String formatCurrency(double amount) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
        return fmt.format(amount);
    }
}