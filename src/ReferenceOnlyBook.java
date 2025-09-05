public class ReferenceOnlyBook extends Book {
    public ReferenceOnlyBook(String title, String isbn) {
        super(title, isbn);
        // Implement your own logic here (if needed) then remove the comment.
    }

    @Override
    public void assignLoan(Loan loan) {
        throw new ItemNotLoanableException("Reference-only book cannot be loaned");
    }

    @Override
    public boolean isAvailableForLoan() {
        return false;
    }

    @Override
    public int getBaseLoanPeriod() {
        return 0;
    }

    @Override
    public String toString() {
        return getTitle() + " (Reference Book, ISBN: " + getISBN() + ")";
    }
}