public class Magazine extends LibraryItem {
    private int issueNumber;
    private static final double DAILY_LATE_FEE = 0.5;
    private static final int BASE_LOAN_PERIOD = 14;
    private static final double MAX_FINE = 10.0;

    public Magazine(String title, int issueNumber) {
        super(title);
        setIssueNumber(issueNumber);
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        if (issueNumber < 0)
            throw new IllegalArgumentException("Issue number must be non-negative");
        this.issueNumber = issueNumber;
    }

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
        return getTitle() + " (Magazine, Issue " + getIssueNumber() + ")";
    }
}