package LibrarayManagementSystem.services;

import LibrarayManagementSystem.exception.BookNotFoundException;
import LibrarayManagementSystem.models.Book;
import LibrarayManagementSystem.repository.FileRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BookService - Manages all book-related operations
 * 
 * SOLID Principle: Single Responsibility
 * - This class ONLY handles Book operations
 * - Does NOT handle members or borrowing logic
 * 
 * NEW: File Persistence
 * - Auto-saves after add/remove operations
 * - Can load data from file on startup
 */
public class BookService {

    private HashMap<Long, Book> books; // Using HashMap for O(1) lookup by ID
    private FileRepository fileRepository;

    public BookService() {
        this.books = new HashMap<>();
        this.fileRepository = new FileRepository();
    }

    /**
     * Constructor with FileRepository injection
     */
    public BookService(FileRepository fileRepository) {
        this.books = new HashMap<>();
        this.fileRepository = fileRepository;
    }

    /**
     * Load books from file
     */
    public void loadFromFile() {
        this.books = fileRepository.loadBooks();
    }

    /**
     * Save books to file
     */
    public void saveToFile() {
        fileRepository.saveBooks(books);
    }

    /**
     * Add a book to the collection
     */
    public void addBook(Book book) {
        try {
            books.put(book.getBookId(), book);
            System.out.println("✅ Book added: " + book.getBookTitle());
            saveToFile(); // Auto-save
        } catch (BookNotFoundException e) {
            System.out.println("❌ Error adding book: " + e.getMessage());
        }
    }

    /**
     * Find book by ID
     * 
     * @throws BookNotFoundException if book doesn't exist
     */
    public Book findBookById(long bookId) throws BookNotFoundException {
        Book book = books.get(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found");
        }
        return book;
    }

    /**
     * Search books by title (partial match)
     */
    public List<Book> findBooksByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getBookTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Search books by author (partial match)
     */
    public List<Book> findBooksByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getBookAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Get all books
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    /**
     * Get all available books
     */
    public List<Book> getAllAvailableBooks() {
        return books.values().stream()
                .filter(Book::isBookAvailable)
                .collect(Collectors.toList());
    }

    /**
     * Remove a book
     */
    public void removeBook(long bookId) throws BookNotFoundException {
        Book book = findBookById(bookId);
        books.remove(bookId);
        System.out.println("✅ Book removed: " + book.getBookTitle());
        saveToFile(); // Auto-save
    }

    /**
     * Display all books
     */
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }

        System.out.println("\n📚 === All Books ===");
        for (Book book : books.values()) {
            System.out.println(book);
        }
        System.out.println("=".repeat(50));
    }

    /**
     * Display available books only
     */
    public void displayAvailableBooks() {
        List<Book> availableBooks = getAllAvailableBooks();

        if (availableBooks.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n📗 === Available Books ===");
        for (Book book : availableBooks) {
            System.out.println(book);
        }
        System.out.println("=".repeat(50));
    }

    /**
     * Get total books count
     */
    public int getTotalBooksCount() {
        return books.size();
    }

    /**
     * Get available books count
     */
    public int getAvailableBooksCount() {
        return (int) books.values().stream()
                .filter(Book::isBookAvailable)
                .count();
    }

    /**
     * Get books HashMap (for direct access if needed)
     */
    public HashMap<Long, Book> getBooks() {
        return books;
    }
}
