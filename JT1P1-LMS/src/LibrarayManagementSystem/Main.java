package LibrarayManagementSystem;

import LibrarayManagementSystem.models.Book;
import LibrarayManagementSystem.models.Member;
import LibrarayManagementSystem.repository.FileRepository;
import LibrarayManagementSystem.services.BookService;
import LibrarayManagementSystem.services.MemberService;
import LibrarayManagementSystem.services.LibraryService;

/**
 * Main class - Demonstrates Library Management System with FILE PERSISTENCE
 * 
 * NEW FEATURE: Data Persistence!
 * ==============================
 * - Books and Members are saved to files
 * - Data persists between program runs
 * - Auto-saves after each operation
 * 
 * Files created:
 * - data/books.dat → All books data
 * - data/members.dat → All members data
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=".repeat(60));
        System.out.println("📚 LIBRARY MANAGEMENT SYSTEM - WITH FILE PERSISTENCE");
        System.out.println("=".repeat(60));

        // Step 1: Create services
        FileRepository fileRepository = new FileRepository();
        BookService bookService = new BookService(fileRepository);
        MemberService memberService = new MemberService(fileRepository);
        LibraryService libraryService = new LibraryService(bookService, memberService);

        // Step 2: Load existing data from files
        System.out.println("\n🔹 Loading existing data...");
        bookService.loadFromFile();
        memberService.loadFromFile();

        // Step 3: Check if we have existing data
        if (bookService.getTotalBooksCount() > 0 || memberService.getTotalMembersCount() > 0) {
            System.out.println("\n✅ Found existing data!");
            libraryService.displayLibraryStats();

            // Display existing books and members
            if (bookService.getTotalBooksCount() > 0) {
                bookService.displayAllBooks();
            }
            if (memberService.getTotalMembersCount() > 0) {
                memberService.displayAllMembers();
            }
        } else {
            System.out.println("\n📝 No existing data. Starting fresh!");

            // Add sample data
            System.out.println("\n🔹 Adding Sample Books...");
            Book book1 = new Book("Java Programming", "Herbert Schildt", "McGraw-Hill", "ISBN001", 3, true);
            Book book2 = new Book("Clean Code", "Robert Martin", "Prentice Hall", "ISBN002", 2, true);
            Book book3 = new Book("Design Patterns", "Gang of Four", "Addison-Wesley", "ISBN003", 1, true);

            bookService.addBook(book1);
            bookService.addBook(book2);
            bookService.addBook(book3);

            // Register sample members
            System.out.println("\n🔹 Registering Sample Members...");
            Member member1 = new Member("Alamin", "01712345678");
            Member member2 = new Member("Rahul", "01798765432");

            memberService.registerMember(member1);
            memberService.registerMember(member2);

            System.out.println("\n✅ Sample data added and saved to files!");
        }

        // Demonstrate operations
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔹 DEMONSTRATING OPERATIONS");
        System.out.println("=".repeat(60));

        // Display current state
        libraryService.displayLibraryStats();

        // You can now:
        // 1. Run the program multiple times - data will persist!
        // 2. Add new books/members - they'll be saved
        // 3. Issue/return books - state is maintained

        System.out.println("\n" + "=".repeat(60));
        System.out.println("💡 TIP: Run this program again - your data will still be there!");
        System.out.println("📁 Data saved in: data/books.dat and data/members.dat");
        System.out.println("=".repeat(60));

        System.out.println("\n✅ Program completed successfully!");
    }
}