package LibrarayManagementSystem.services;

import LibrarayManagementSystem.exception.BookNotAvailableException;
import LibrarayManagementSystem.exception.BookNotFoundException;
import LibrarayManagementSystem.models.Book;
import LibrarayManagementSystem.models.Library;

public class BookService {

    private Library library;

    public BookService(Library library) {
        this.library = library;
    }

    public void addBookToLibrary(Book book) {
        library.addBook(book);
    }

    public boolean isBookAvailable(int bookId) {
        Book book = library.getBookById(bookId);
        return book != null && book.isBookAvailable();
    }

    public void borrowCopy(int bookId) throws BookNotAvailableException, BookNotFoundException {
        Book book = library.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book not found");
        }
        if (!book.isBookAvailable()) {
            throw new BookNotAvailableException("Book is not available");
        }
        book.setBookCopiesAvailable(book.getBookCopiesAvailable() - 1);
    }

    public void returnCopy() {
        if (book.getBookCopiesAvailable() < book.getBookCopiesTotal()) {
            book.setBookCopiesAvailable(book.getBookCopiesAvailable() + 1);
        }
        book.setBookAvailable(true);
    }

    public void getBookDetails() {
        System.out.print("Book ID: " + book.getBookId());
        System.out.print("\tBook Title: " + book.getBookTitle());
        System.out.print("\tBook Author: " + book.getBookAuthor());
        System.out.print("\tBook Publisher: " + book.getBookPublisher());
        System.out.print("\tBook ISBN: " + book.getBookISBN());
        System.out.print("\tBook Total Copies: " + book.getBookCopiesTotal());
        System.out.print("\tBook Available copies: " + book.getBookCopiesAvailable());
        System.out.println("\tBook Available: " + book.isBookAvailable() + "\n");
    }

    public void getBookDetailsById(int bookId) {
        // This method would typically look up a book by its ID from a data source.
        // For demonstration purposes, we'll just print the details of the current book.
        if (book.getBookId() == bookId) {
            getBookDetails();
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }

    }

}
