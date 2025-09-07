public class Academic extends LibraryUser {

    // Constants
    private static final int LOAN_LIMIT = 10;
    private static final int ADDITIONAL_LOAN_TIME = 14;
    private static final double FINE_MOD = 1.0;
    private static final double SUSPENSION_LIMIT = 20.0;
    private static final int MAGAZINE_LOAN_PERIOD = 28;
    private static final int DVD_LOAN_PERIOD = 21;
    private static final int BOOK_LOAN_PERIOD = 35;

    // Constructor
    public Academic(String name) {
        super(name);
    }

    // Methods
    @Override
    public int getLoanPeriodForItem(LibraryItem item) {
        if (item == null)
            throw new IllegalArgumentException("LibraryItem cannot be null");
        else if (item instanceof Magazine) {
            return MAGAZINE_LOAN_PERIOD;
        } else if (item instanceof DVD) {
            return DVD_LOAN_PERIOD;
        } else if (item instanceof Book) {
            return BOOK_LOAN_PERIOD;
        }
        return ADDITIONAL_LOAN_TIME;
    }

    @Override
    public int getLoanLimit() {
        return LOAN_LIMIT;
    }

    @Override
    public double getFineRateModifier() {
        return FINE_MOD;
    }

    @Override
    public double getFineSuspensionLimit() {
        return SUSPENSION_LIMIT;
    }

    @Override
    public String getUserRole() {
        return "Academic";
    }
}