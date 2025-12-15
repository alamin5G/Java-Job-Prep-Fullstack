package LibrarayManagementSystem.models;


import LibrarayManagementSystem.exception.BookNotAvailableException;
import LibrarayManagementSystem.exception.BookNotFoundException;

public class Book {
    private long bookId = 0L;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private String bookISBN;
    private int bookCopiesTotal;
    private int bookCopiesAvailable;
    private boolean bookAvailable;

    //default constructor
    public Book (){}

    //constructor of book class
    public Book( String bookTitle, String bookAuthor, String bookPublisher, String bookISBN, int bookCopies, boolean bookAvailable) {
        ++bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookISBN = bookISBN;
        bookCopiesAvailable = bookCopies;
        bookCopiesTotal = bookCopies;
        this.bookAvailable = bookAvailable;
    }


    //public method of book class
    public boolean isBookAvailable() {
        return bookAvailable && bookCopiesAvailable > 0;
    }

    public void borrowCopy() throws BookNotAvailableException, BookNotFoundException {
        if (!bookAvailable) {
            throw new BookNotAvailableException("Book is not available");
        }
        bookCopiesAvailable--;
    }

    public void returnCopy(){
        if (bookCopiesAvailable < bookCopiesTotal) bookCopiesAvailable++;
        bookAvailable = true;
    }





    public long getBookId() {
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

}
