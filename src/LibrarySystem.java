import java.util.*;

public class LibrarySystem {

    // The date manager is used in this assignment to provide an easier method for
    // you to work with time logic.
    // It keeps track of the current day, and allows you to progress the current day
    // with dateManager.advanceDays(numberOfDays).
    // This makes it easy for you to count overdue days, and makes it easier for you
    // to test the system.
    //
    // In a real-world application, this would be replaced with a more powerful
    // implementation of date/time logic.
    private DateManager dateManager = new DateManager();

    // Already implemented - returns access to the date manager handling the current
    // simulated date for the library system.
    public DateManager getDateManager() {
        return dateManager;
    }

    // LibrarySystem Constructor.
    public LibrarySystem() {
        // initialize collections
        this.users = new java.util.ArrayList<>();
        this.items = new java.util.ArrayList<>();
    }

    private java.util.List<LibraryUser> users;
    private java.util.List<LibraryItem> items;

    // This method should add the specified item to the library system (or throw an
    // exception if the item is not valid or already added).
    public void addItem(LibraryItem item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");
        if (items.contains(item))
            throw new IllegalArgumentException("Item already added");
        items.add(item);
    }

    // This method should add a user to the library system (or throw an exception if
    // the user is not valid or already added).
    public void addUser(LibraryUser user) {
        if (user == null)
            throw new IllegalArgumentException("User cannot be null");
        if (users.contains(user))
            throw new IllegalArgumentException("User already added");
        users.add(user);
    }

    // This method should loan a specific library item to a specific user. If the
    // loan is not valid, it should throw
    // an appropriate exception (e.g., it should throw LoanLimitExceededException if
    // the user is already at their borrow limit).
    public void loanItem(LibraryItem item, LibraryUser user) {
        if (item == null || user == null)
            throw new IllegalArgumentException("Item and user must not be null");
        // create loan and process
        Loan loan = new Loan(item, user, dateManager);
        loan.processLoan();
    }

    // Returns the item to the library. This should throw an appropriate exception
    // if the returning item is not valid.
    // If the item is overdue, you do not need to write code to handle paying the
    // fines. Assume that the fines were
    // paid by the user during the return process.
    public void returnItem(LibraryItem item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");
        Loan loan = item.getCurrentLoan();
        if (loan == null)
            throw new IllegalArgumentException("Item is not currently on loan");
        loan.processReturn();
    }

    // This method returns a list of all library users in the system.
    // This method should return an unmodifiable list to prevent modification.
    public List<LibraryUser> getUsers() {
        return java.util.Collections.unmodifiableList(users);
    }

    // This method should return a list of all library items in the system.
    // This method should return an unmodifiable list to prevent modification.
    public List<LibraryItem> getLibraryItems() {
        return java.util.Collections.unmodifiableList(items);
    }

    // This method should return a list of all library items which can currently be
    // borrowed in the system.
    // The method should return an unmodifiable list to prevent modification.
    public List<LibraryItem> getBorrowableItems() {
        java.util.List<LibraryItem> borrowable = new java.util.ArrayList<>();
        for (LibraryItem it : items)
            if (it.isAvailableForLoan())
                borrowable.add(it);
        return java.util.Collections.unmodifiableList(borrowable);
    }
}