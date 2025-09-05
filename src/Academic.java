public class Academic extends LibraryUser {

    public Academic(String name) {
        super(name);
    }

    @Override
    public int getLoanLimit() {
        return 10;
    }

    @Override
    public int getLoanPeriodForItem(LibraryItem item) {
        return 14;
    }

    @Override
    public double getFineRateModifier() {
        return 1.0;
    }

    @Override
    public double getFineSuspensionLimit() {
        return 20.0;
    }

    @Override
    public String getUserRole() {
        return "Academic";
    }
}