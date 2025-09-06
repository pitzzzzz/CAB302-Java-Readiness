public class Student extends LibraryUser {
    public Student(String name) {
        super(name);
        // Implement your own logic here (if needed) then remove the comment.
    }

    // Constants
    private static final int LOAN_LIMIT = 5;
    private static final int ADDITIONAL_LOAN_TIME = 7;
    private static final double FINE_MOD = 0.75;
    private static final double SUSPENSION_LIMIT = 10.0;
    private static final int MAGAZINE_LOAN_PERIOD = 21;
    private static final int DVD_LOAN_PERIOD = 14;
    private static final int BOOK_LOAN_PERIOD = 28;

    // Methods (overrides)
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
        return "Student";
    }
}