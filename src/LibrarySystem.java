import java.util.*;

public class LibrarySystem {

    // Fields
    private DateManager dateManager = new DateManager();

    private java.util.List<LibraryUser> users;
    private java.util.List<LibraryItem> items;

    // Getter
    public DateManager getDateManager() {
        return dateManager;
    }

    // Constructor
    public LibrarySystem() {
        this.users = new java.util.ArrayList<>();
        this.items = new java.util.ArrayList<>();
    }

    // Methods
    public void addItem(LibraryItem item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");
        if (items.contains(item))
            throw new IllegalArgumentException("Item already added");
        items.add(item);
    }

    public void addUser(LibraryUser user) {
        if (user == null)
            throw new IllegalArgumentException("User cannot be null");
        if (users.contains(user))
            throw new IllegalArgumentException("User already added");
        users.add(user);
    }

    public void loanItem(LibraryItem item, LibraryUser user) {
        if (item == null || user == null)
            throw new IllegalArgumentException("Item and user must not be null");
        if (!items.contains(item))
            throw new IllegalArgumentException("Item is not registered in the system");
        if (!users.contains(user))
            throw new IllegalArgumentException("User is not registered in the system");
        // create loan and process
        Loan loan = new Loan(item, user, dateManager);
        loan.processLoan();
    }

    public void returnItem(LibraryItem item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");
        Loan loan = item.getCurrentLoan();
        if (loan == null)
            throw new IllegalArgumentException("Item is not currently on loan");
        loan.processReturn();
    }

    public List<LibraryUser> getUsers() {
        return java.util.Collections.unmodifiableList(users);
    }

    public List<LibraryItem> getLibraryItems() {
        return java.util.Collections.unmodifiableList(items);
    }

    public List<LibraryItem> getBorrowableItems() {
        java.util.List<LibraryItem> borrowable = new java.util.ArrayList<>();
        for (LibraryItem it : items)
            if (it.isAvailableForLoan())
                borrowable.add(it);
        return java.util.Collections.unmodifiableList(borrowable);
    }
}