import java.util.*;

public abstract class LibraryUser {
    private String name;
    private java.util.List<Loan> loans = new java.util.ArrayList<>();

    // LibraryUser Constructor
    public LibraryUser(String name) {
        setName(name);
    }

    // Setter for the user’s name. Should throw an appropriate exception if the name
    // is not valid.
    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name must not be null or empty");
        this.name = name.trim();
    }

    // Getter method for the user’s name.
    public String getName() {
        return name;
    }

    // Assigns the specified loan to the user. This method should perform
    // appropriate validation checks to ensure that
    // the loan is valid for the user, and throw appropriate exceptions as needed.
    public void assignLoan(Loan loan) {
        if (loan == null)
            throw new IllegalArgumentException("Loan cannot be null");
        if (loan.getUser() != this)
            throw new IllegalArgumentException("Loan does not reference this user");
        if (isAtLoanLimit())
            throw new BorrowLimitExceededException("User at loan limit");
        if (isSuspended())
            throw new AccountSuspendedException("User account suspended");
        loans.add(loan);
    }

    // Removes the specified loan from the user. Should throw an appropriate
    // exception if the loan is not valid.
    public void removeLoan(Loan loan) {
        if (loan == null)
            throw new IllegalArgumentException("Loan cannot be null");
        if (!loans.remove(loan))
            throw new IllegalArgumentException("Loan not found for this user");
    }

    // Returns true if the user’s current fine total equals or exceeds their
    // suspension limit.
    public boolean isSuspended() {
        return getTotalFines() >= getFineSuspensionLimit();
    }

    // Returns a list of loans currently active for the user.
    // This should be an unmodifiable list to prevent unexpected modification.
    public List<Loan> getLoans() {
        return java.util.Collections.unmodifiableList(loans);
    }

    // Returns the total amount of fines that the user has accrued.
    public double getTotalFines() {
        double total = 0.0;
        for (Loan l : loans)
            total += l.getLoanFine();
        return total;
    }

    // Returns true if the user is already at their loan limit and cannot borrow
    // more items.
    public boolean isAtLoanLimit() {
        return loans.size() >= getLoanLimit();
    }

    // Returns a human-readable name for the user type. This has already been
    // implemented for you.
    @Override
    public String toString() {
        return getName() + " (" + getUserRole() + ")";
    }

    public abstract int getLoanPeriodForItem(LibraryItem item);

    // Returns the loan limit for the user.
    // Refer to the assignment specifications to determine the appropriate value for
    // the user type.
    public abstract int getLoanLimit();

    // Returns the fine rate modifier for the user.
    // Refer to the assignment specifications to determine the appropriate value for
    // the user type.
    public abstract double getFineRateModifier();

    // Returns the total fines the user can accrue before they are suspended.
    // Refer to the assignment specifications to determine the appropriate value for
    // the user type.
    public abstract double getFineSuspensionLimit();

    // Returns a human-readable name for the user type. This has already been
    // implemented for you.
    public abstract String getUserRole();
}