public class Loan {

    // Fields
    private final LibraryItem libraryItem;
    private final LibraryUser user;
    private final DateManager dateManager;

    private int checkoutDay = -1;
    private int returnDay = -1;

    // Getters
    public int getCheckoutDay() {
        return checkoutDay;
    }

    public int getReturnDay() {
        return returnDay;
    }

    public LibraryUser getUser() {
        return user;
    }

    public LibraryItem getLibraryItem() {
        return libraryItem;
    }

    // Constructor
    public Loan(LibraryItem libraryItem, LibraryUser user, DateManager dateManager) {
        if (libraryItem == null) {
            throw new IllegalArgumentException("LibraryItem must not be null");
        } else if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        } else if (dateManager == null) {
            throw new IllegalArgumentException("DateManager must not be null");

        }
        this.libraryItem = libraryItem;
        this.user = user;
        this.dateManager = dateManager;
    }

    // Methods
    public void processLoan() {
        if (this.checkoutDay != -1)
            throw new IllegalStateException("Loan has already been processed");
        // record the checkout and return days at the time the loan is processed
        this.checkoutDay = dateManager.getCurrentDay();
        this.returnDay = checkoutDay + libraryItem.getBaseLoanPeriod() + user.getLoanPeriodForItem(libraryItem);

        user.assignLoan(this);
        try {
            libraryItem.assignLoan(this);
        } catch (RuntimeException e) {
            try {
                user.removeLoan(this);
            } catch (Exception ignore) {
            }
            throw e;
        }
    }

    public void processReturn() {
        // Remove loan from user and item
        user.removeLoan(this);
        libraryItem.removeLoan();
    }

    public boolean isOverdue() {
        return dateManager.getCurrentDay() > getReturnDay();
    }

    public double getLoanFine() {
        int overdueDays = getOverdueDays();
        if (overdueDays <= 0)
            return 0.0;
        double fine = overdueDays * libraryItem.getDailyLateFee() * user.getFineRateModifier();
        double max = libraryItem.getMaximumFine();
        return Math.min(fine, max);
    }

    public int getOverdueDays() {
        int diff = dateManager.getCurrentDay() - getReturnDay();
        return Math.max(0, diff);
    }
}