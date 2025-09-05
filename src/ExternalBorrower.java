public class ExternalBorrower extends LibraryUser {

    public ExternalBorrower(String name) {
        super(name);
        // Implement your own additional logic here then remove the comment.
    }

    @Override
    public int getLoanPeriodForItem(LibraryItem item) {
        return 0;
    }

    @Override
    public int getLoanLimit() {
        return 2;
    }

    @Override
    public double getFineRateModifier() {
        return 2.0;
    }

    @Override
    public double getFineSuspensionLimit() {
        return 5.0;
    }

    @Override
    public String getUserRole() {
        return "External Borrower";
    }
}