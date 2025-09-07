public class DVD extends LibraryItem {

    // Fields
    private int runtimeMinutes;

    // Constants
    private static final double DAILY_LATE_FEE = 1.0;
    private static final int BASE_LOAN_PERIOD = 7;
    private static final double MAX_FINE = 30.0;

    // Getter
    public int getRuntimeLength() {
        return runtimeMinutes;
    }

    // Setter
    public void setRuntimeLength(int runtimeMinutes) {
        if (runtimeMinutes <= 0)
            throw new IllegalArgumentException("Runtime must be non-negative");
        this.runtimeMinutes = runtimeMinutes;
    }

    // Constructor
    public DVD(String title, int runtimeMinutes) {
        super(title);
        setRuntimeLength(runtimeMinutes);
    }

    // Methods
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
        return getTitle() + " (DVD, Runtime Length: " + getRuntimeLength() + " Minutes)";
    }
}