import java.util.*;

public abstract class LibraryUser {

    // Fields
    private String name;
    private java.util.List<Loan> loans = new java.util.ArrayList<>();

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name must not be null or empty");
        this.name = name.trim();
    }

    // Constructor
    public LibraryUser(String name) {
        setName(name);
    }

    // Methods
    public void assignLoan(Loan loan) {
        if (loan == null)
            throw new IllegalArgumentException("Loan cannot be null");
        if (loan.getUser() != this)
            throw new IllegalArgumentException("Loan does not reference this user");
        if (isAtLoanLimit())
            throw new LoanLimitExceededException("User at loan limit");
        if (isSuspended())
            throw new AccountSuspendedException("User account suspended");
        loans.add(loan);
    }

    public void removeLoan(Loan loan) {
        if (loan == null)
            throw new IllegalArgumentException("Loan cannot be null");
        if (!loans.remove(loan))
            throw new ItemNotLoanableException("Loan not found for this user");
    }

    public boolean isSuspended() {
        return getTotalFines() >= getFineSuspensionLimit();
    }

    public List<Loan> getLoans() {
        return java.util.Collections.unmodifiableList(loans);
    }

    public double getTotalFines() {
        double total = 0.0;
        for (Loan l : loans)
            total += l.getLoanFine();
        return total;
    }

    public boolean isAtLoanLimit() {
        return loans.size() >= getLoanLimit();
    }

    @Override
    public String toString() {
        return getName() + " (" + getUserRole() + ")";
    }

    public abstract int getLoanPeriodForItem(LibraryItem item);

    public abstract int getLoanLimit();

    public abstract double getFineRateModifier();

    public abstract double getFineSuspensionLimit();

    public abstract String getUserRole();

    // Helper methods for formatted outputs
    public String getFineSuspensionLimitFormatted() {
        return String.format("$%.2f", getFineSuspensionLimit());
    }
}