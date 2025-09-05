public class Loan {
    private final LibraryItem libraryItem;
    private final LibraryUser user;
    private final DateManager dateManager;
    private final int checkoutDay;
    private final int returnDay;

    // Loan constructor.
    public Loan(LibraryItem libraryItem, LibraryUser user, DateManager dateManager) {
        if (libraryItem == null || user == null || dateManager == null)
            throw new IllegalArgumentException("Arguments must not be null");
        this.libraryItem = libraryItem;
        this.user = user;
        this.dateManager = dateManager;
        this.checkoutDay = dateManager.getCurrentDay();
        // return day = checkout + base period + user additional
        this.returnDay = checkoutDay + libraryItem.getBaseLoanPeriod() + user.getLoanPeriodForItem(libraryItem);
    }

    // This processes the loan, recording the loan against the item and against the
    // user. This should throw appropriate
    // exceptions if the loan cannot be processed (e.g., if a user is already at
    // their loan limit). Note: You do not
    // need to throw the errors from this method specifically, as long as the errors
    // are thrown when the method is called.
    public void processLoan() {
        // Validate availability and user state performed in assign methods
        // Assign to item then to user
        libraryItem.assignLoan(this);
        user.assignLoan(this);
    }

    // This handles the logic for returning the item. This will remove the record of
    // the loan from both the user
    // and the item.
    public void processReturn() {
        // Remove loan from user and item
        user.removeLoan(this);
        libraryItem.removeLoan();
    }

    // Should return true if the item is currently overdue. An item is not overdue
    // until the current day
    // exceeds the return day.
    public boolean isOverdue() {
        return dateManager.getCurrentDay() > getReturnDay();
    }

    // Should return the current value of the fine. If the item currently hasn’t
    // accrued a fine because it is not
    // yet overdue, this should return 0.
    public double getLoanFine() {
        int overdueDays = getOverdueDays();
        if (overdueDays <= 0)
            return 0.0;
        double fine = overdueDays * libraryItem.getDailyLateFee() * user.getFineRateModifier();
        double max = libraryItem.getMaximumFine();
        return Math.min(fine, max);
    }

    // Should return the current number of days that the item is overdue (or zero if
    // not yet overdue).
    public int getOverdueDays() {
        int diff = dateManager.getCurrentDay() - getReturnDay();
        return Math.max(0, diff);
    }

    // Should return the day that the library item was borrowed.
    public int getCheckoutDay() {
        return checkoutDay;
    }

    // Should return the day at which the item needs to be returned before fines
    // begin.
    public int getReturnDay() {
        return returnDay;
    }

    // Getter method for the user associated with the loan.
    public LibraryUser getUser() {
        return user;
    }

    // Getter method for the library item associated with the loan.
    public LibraryItem getLibraryItem() {
        return libraryItem;
    }
}