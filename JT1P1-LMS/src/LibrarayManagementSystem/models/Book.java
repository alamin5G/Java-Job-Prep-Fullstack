package LibrarayManagementSystem.models;

import LibrarayManagementSystem.exception.BookNotFoundException;
import java.io.Serializable;

public class Book implements Serializable {
    
    private static final long serialVersionUID = 1L;  // For version control

    private static long bookIdCounter = 0L;
    private long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private String bookISBN;
    private int bookCopiesTotal;
    private int bookCopiesAvailable;
    private boolean bookAvailable;

    // default constructor
    public Book() {
    }

    // constructor of book class
    public Book(String bookTitle, String bookAuthor, String bookPublisher, String bookISBN, int bookCopies,
            boolean bookAvailable) {
        this.bookId = ++bookIdCounter;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookISBN = bookISBN;
        bookCopiesAvailable = bookCopies;
        bookCopiesTotal = bookCopies;
        this.bookAvailable = bookAvailable;
    }

    public boolean isBookAvailable() {
        return bookAvailable;
    }

    public long getBookId() throws BookNotFoundException {
        if (bookId <= 0) {
            throw new BookNotFoundException("Book ID is not set or invalid.");
        }
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public int getBookCopiesTotal() {
        return bookCopiesTotal;
    }

    public void setBookCopiesTotal(int bookCopiesTotal) {
        this.bookCopiesTotal = bookCopiesTotal;
    }

    public int getBookCopiesAvailable() {
        return bookCopiesAvailable;
    }

    public void setBookCopiesAvailable(int bookCopiesAvailable) {
        this.bookCopiesAvailable = bookCopiesAvailable;
        // Auto-update availability based on copies
        this.bookAvailable = (bookCopiesAvailable > 0);
    }

    public void setBookAvailable(boolean bookAvailable) {
        this.bookAvailable = bookAvailable;
    }

    /**
     * Decrement available copy when book is borrowed
     * Business logic: Book manages its own state
     */
    public void decrementCopy() {
        if (bookCopiesAvailable > 0) {
            bookCopiesAvailable--;
            // Update availability status
            bookAvailable = (bookCopiesAvailable > 0);
        }
    }

    /**
     * Increment available copy when book is returned
     * Business logic: Book manages its own state
     */
    public void incrementCopy() {
        if (bookCopiesAvailable < bookCopiesTotal) {
            bookCopiesAvailable++;
            // Update availability status
            bookAvailable = true;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + bookTitle + '\'' +
                ", author='" + bookAuthor + '\'' +
                ", ISBN='" + bookISBN + '\'' +
                ", available=" + bookCopiesAvailable + "/" + bookCopiesTotal +
                '}';
    }

}
