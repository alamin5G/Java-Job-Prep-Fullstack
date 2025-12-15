package LibrarayManagementSystem;

import LibrarayManagementSystem.models.Book;
import LibrarayManagementSystem.models.Library;
import LibrarayManagementSystem.models.Member;
import LibrarayManagementSystem.services.BookService;
import LibrarayManagementSystem.services.LibraryService;
import LibrarayManagementSystem.services.MemberService;

/**
 * Main Class - Demonstration of Complete Architecture
 * 
 * এইখানে তুমি দেখবে কীভাবে সব classes একসাথে কাজ করে!
 * 
 * ARCHITECTURE FLOW:
 * ------------------
 * 1. Library তৈরি করো (data container)
 * 2. Services তৈরি করো (business logic handlers)
 * 3. Data add করো (books, members)
 * 4. Operations perform করো (issue, return, search)
 * 
 * এইটা হলো PROPER SEPARATION OF CONCERNS! 🎯
 */
public class Main {

    public static void main(String[] args) {
        
        System.out.println("=".repeat(80));
        System.out.println("📚 LIBRARY MANAGEMENT SYSTEM - ARCHITECTURE DEMONSTRATION");
        System.out.println("=".repeat(80));
        
        // ========== STEP 1: Create Library (Data Container) ==========
        System.out.println("\n🔧 Step 1: Creating Library (Data Container)...");
        Library library = new Library();
        System.out.println("✅ Library created!");
        
        // ========== STEP 2: Create Services (Business Logic) ==========
        System.out.println("\n🔧 Step 2: Creating Services...");
        BookService bookService = new BookService(library);
        MemberService memberService = new MemberService(library);
        LibraryService libraryService = new LibraryService(bookService, memberService);
        System.out.println("✅ All services created!");
        System.out.println("   - BookService (handles book operations)");
        System.out.println("   - MemberService (handles member operations)");
        System.out.println("   - LibraryService (coordinates both services)");
        
        // ========== STEP 3: Add Books ==========
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📖 Step 3: Adding Books to Library...");
        System.out.println("=".repeat(80));
        
        Book book1 = new Book("Core Java", "Herbert Schildt", "Oracle Press", "978-0071808552", 5, true);
        Book book2 = new Book("Effective Java", "Joshua Bloch", "Addison-Wesley", "978-0134685991", 3, true);
        Book book3 = new Book("Spring Boot in Action", "Craig Walls", "Manning", "978-1617292545", 2, true);
        Book book4 = new Book("Clean Code", "Robert Martin", "Prentice Hall", "978-0132350884", 4, true);
        
        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);
        
        // ========== STEP 4: Register Members ==========
        System.out.println("\n" + "=".repeat(80));
        System.out.println("👥 Step 4: Registering Members...");
        System.out.println("=".repeat(80));
        
        Member member1 = new Member("Alamin", "01700000001");
        Member member2 = new Member("Karim", "01700000002");
        Member member3 = new Member("Rahim", "01700000003");
        
        memberService.registerMember(member1);
        memberService.registerMember(member2);
        memberService.registerMember(member3);
        
        // ========== STEP 5: Display Library Stats ==========
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📊 Step 5: Library Statistics");
        System.out.println("=".repeat(80));
        libraryService.displayLibraryStats();
        
        // ========== STEP 6: Issue Books ==========
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📤 Step 6: Issuing Books to Members...");
        System.out.println("=".repeat(80));
        
        try {
            // Alamin borrows "Core Java" (ID: 1)
            System.out.println("\n--- Issuing Book ID 1 to Member ID " + member1.getMemberId() + " ---");
            libraryService.issueBook(book1.getBookId(), member1.getMemberId());
            
            // Alamin borrows "Effective Java" (ID: 2)
            System.out.println("\n--- Issuing Book ID 2 to Member ID " + member1.getMemberId() + " ---");
            libraryService.issueBook(book2.getBookId(), member1.getMemberId());
            
            // Karim borrows "Spring Boot in Action" (ID: 3)
            System.out.println("\n--- Issuing Book ID 3 to Member ID " + member2.getMemberId() + " ---");
            libraryService.issueBook(book3.getBookId(), member2.getMemberId());
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        
        // ========== STEP 7: Show Member's Borrowed Books ==========
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📚 Step 7: Showing Member's Borrowed Books...");
        System.out.println("=".repeat(80));
        
        try {
            System.out.println("\n--- Books borrowed by Alamin (Member ID: " + member1.getMemberId() + ") ---");
            libraryService.showMemberBorrowedBooks(member1.getMemberId());
            
            System.out.println("\n--- Books borrowed by Karim (Member ID: " + member2.getMemberId() + ") ---");
            libraryService.showMemberBorrowedBooks(member2.getMemberId());
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        
        // ========== STEP 8: Return a Book ==========
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📥 Step 8: Returning Books...");
        System.out.println("=".repeat(80));
        
        try {
            // Alamin returns "Core Java"
            System.out.println("\n--- Alamin returning Book ID " + book1.getBookId() + " ---");
            libraryService.returnBook(book1.getBookId(), member1.getMemberId());
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        
        // ========== STEP 9: Show Updated Borrowed Books ==========
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📚 Step 9: Updated Borrowed Books List...");
        System.out.println("=".repeat(80));
        
        try {
            System.out.println("\n--- Books STILL borrowed by Alamin ---");
            libraryService.showMemberBorrowedBooks(member1.getMemberId());
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        
        // ========== STEP 10: Search Operations ==========
        System.out.println("\n" + "=".repeat(80));
        System.out.println("🔍 Step 10: Search Operations...");
        System.out.println("=".repeat(80));
        
        System.out.println("\n--- Searching books with 'Java' in title ---");
        var javaBooks = bookService.findBooksByTitle("Java");
        System.out.println("Found " + javaBooks.size() + " books:");
        for (Book book : javaBooks) {
            System.out.println("   - " + book.getBookTitle() + " by " + book.getBookAuthor());
        }
        
        System.out.println("\n--- Searching members with 'am' in name ---");
        var foundMembers = memberService.findMembersByName("am");
        System.out.println("Found " + foundMembers.size() + " members:");
        for (Member member : foundMembers) {
            System.out.println("   - " + member.getMemberName() + " (Phone: " + member.getMemberPhone() + ")");
        }
        
        // ========== STEP 11: Display All Available Books ==========
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📗 Step 11: All Available Books...");
        System.out.println("=".repeat(80));
        
        var availableBooks = bookService.getAllAvailableBooks();
        System.out.println("\nTotal available books: " + availableBooks.size());
        for (Book book : availableBooks) {
            try {
                System.out.println("   - " + book.getBookTitle() + " (Copies: " + book.getBookCopiesAvailable() + ")");
            } catch (Exception e) {
                // Skip
            }
        }
        
        // ========== FINAL STATS ==========
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📊 FINAL LIBRARY STATISTICS");
        System.out.println("=".repeat(80));
        libraryService.displayLibraryStats();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("✅ DEMONSTRATION COMPLETE!");
        System.out.println("=".repeat(80));
        
        System.out.println("\n💡 KEY TAKEAWAYS:");
        System.out.println("   1. Library = Data container (holds books & members)");
        System.out.println("   2. BookService = Book operations (add, find, display)");
        System.out.println("   3. MemberService = Member operations (register, find)");
        System.out.println("   4. LibraryService = Coordinator (issue, return books)");
        System.out.println("   5. Services don't store data, they OPERATE on Library's data!");
        System.out.println("\n   This is SEPARATION OF CONCERNS! 🎯");
    }
}
