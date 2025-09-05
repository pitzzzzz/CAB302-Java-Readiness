public class Student extends LibraryUser {
    public Student(String name) {
        super(name);
        // Implement your own logic here (if needed) then remove the comment.
    }

    private static final int LOAN_LIMIT = 5;
    private static final int ADDITIONAL_LOAN_TIME = 7; // days
    private static final double FINE_MOD = 0.75;
    private static final double SUSPENSION_LIMIT = 10.0;

    public int getLoanLimit() {
        return LOAN_LIMIT;
    }

    public int getLoanPeriodForItem(LibraryItem item) {
        return ADDITIONAL_LOAN_TIME;
    }

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