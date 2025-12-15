package LibrarayManagementSystem.services;

import LibrarayManagementSystem.exception.BookNotAvailableException;
import LibrarayManagementSystem.exception.BookNotFoundException;
import LibrarayManagementSystem.exception.MemberNotFoundException;
import LibrarayManagementSystem.models.Book;
import LibrarayManagementSystem.models.Member;

/**
 * LibraryService - The ORCHESTRATOR
 * 
 * ⭐⭐⭐ THIS IS THE KEY CLASS TO UNDERSTAND! ⭐⭐⭐
 * 
 * WHY THIS CLASS EXISTS?
 * ----------------------
 * Think: Library তে book issue/return করতে কী লাগে?
 * - Book information (BookService)
 * - Member information (MemberService)
 * - দুইটাকে coordinate করা!
 * 
 * CONCEPT: Service Coordination
 * ------------------------------
 * BookService = শুধু books নিয়ে কাজ করে
 * MemberService = শুধু members নিয়ে কাজ করে
 * LibraryService = দুইটাকে একসাথে ব্যবহার করে complex operations করে
 * 
 * EXAMPLE:
 * --------
 * issueBook() method এ কী হয়?
 * 1. BookService থেকে book খুঁজো
 * 2. MemberService থেকে member খুঁজো
 * 3. Check: book available আছে?
 * 4. Book এর copy কমাও
 * 5. Member এর list এ book ID add করো
 * 
 * এই পুরো flow টা coordinate করে LibraryService!
 * 
 * এইটাই হলো SEPARATION OF CONCERNS এর power! 💪
 */
public class LibraryService {

    private BookService bookService;      // Book operations এর জন্য
    private MemberService memberService;  // Member operations এর জন্য
    
    /**
     * Constructor - Both services inject করা হয়
     * 
     * WHY? LibraryService নিজে data store করে না,
     * সে অন্য services থেকে data নিয়ে coordinate করে
     */
    public LibraryService(BookService bookService, MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
    }
    
    // ==================== MAIN LIBRARY OPERATIONS ====================
    
    /**
     * Issue book to member - CORE FEATURE!
     * 
     * FLOW (Step by step logic):
     * ---------------------------
     * 1. Find book (BookService থেকে)
     * 2. Find member (MemberService থেকে)
     * 3. Check: book available?
     * 4. Check: member already borrowed this book?
     * 5. Update book: decrease copy
     * 6. Update member: add book ID to borrowed list
     * 
     * এইটাই হলো COORDINATION! 🎯
     */
    public void issueBook(long bookId, long memberId) {
        try {
            // Step 1: Find book using BookService
            Book book = bookService.findBookById(bookId);
            
            // Step 2: Find member using MemberService
            Member member = memberService.findMemberById(memberId);
            
            // Step 3: Check if book is available
            if (!book.isBookAvailable() || book.getBookCopiesAvailable() <= 0) {
                throw new BookNotAvailableException(
                    "❌ Book '" + book.getBookTitle() + "' is not available!"
                );
            }
            
            // Step 4: Check if member already borrowed this book
            if (member.hasBorrowedBook(bookId)) {
                System.out.println("❌ Member has already borrowed this book!");
                return;
            }
            
            // Step 5: Update book - decrease available copies
            book.decrementCopy();
            
            // Step 6: Update member - add book to borrowed list
            member.addBorrowedBook(bookId);
            
            // Success!
            System.out.println("\n✅ Book issued successfully!");
            System.out.println("   Member: " + member.getMemberName());
            System.out.println("   Book: " + book.getBookTitle());
            System.out.println("   Copies remaining: " + book.getBookCopiesAvailable());
            
        } catch (BookNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (MemberNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (BookNotAvailableException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
    
    /**
     * Return book from member - CORE FEATURE!
     * 
     * FLOW:
     * -----
     * 1. Find book
     * 2. Find member
     * 3. Check: member actually borrowed this book?
     * 4. Update book: increase copy
     * 5. Update member: remove book ID from list
     */
    public void returnBook(long bookId, long memberId) {
        try {
            // Step 1: Find book
            Book book = bookService.findBookById(bookId);
            
            // Step 2: Find member
            Member member = memberService.findMemberById(memberId);
            
            // Step 3: Check if member actually borrowed this book
            if (!member.hasBorrowedBook(bookId)) {
                System.out.println("❌ Member did not borrow this book!");
                return;
            }
            
            // Step 4: Update book - increase available copies
            book.incrementCopy();
            
            // Step 5: Update member - remove book from borrowed list
            member.removeBorrowedBook(bookId);
            
            // Success!
            System.out.println("\n✅ Book returned successfully!");
            System.out.println("   Member: " + member.getMemberName());
            System.out.println("   Book: " + book.getBookTitle());
            System.out.println("   Copies available now: " + book.getBookCopiesAvailable());
            
        } catch (BookNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (MemberNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    // ==================== DISPLAY OPERATIONS ====================
    
    /**
     * Show member's borrowed books with full details
     * 
     * WHY HERE? এটা Member + Book দুইটার coordination দরকার
     */
    public void showMemberBorrowedBooks(long memberId) {
        memberService.displayMemberBorrowedBooks(memberId, bookService);
    }
    
    /**
     * Display library statistics
     * 
     * USES BOTH SERVICES to gather complete info
     */
    public void displayLibraryStats() {
        System.out.println("\n📊 === Library Statistics ===");
        System.out.println("   Total Books: " + bookService.getTotalBooksCount());
        System.out.println("   Available Books: " + bookService.getAvailableBooksCount());
        System.out.println("   Total Members: " + memberService.getTotalMembersCount());
        System.out.println("=".repeat(50));
    }
    
    /**
     * Check if a book can be issued
     * 
     * VALIDATION logic - checks before actually issuing
     */
    public boolean canIssueBook(long bookId, long memberId) {
        try {
            Book book = bookService.findBookById(bookId);
            Member member = memberService.findMemberById(memberId);
            
            // All conditions for successful issue
            return book.isBookAvailable() 
                && book.getBookCopiesAvailable() > 0
                && !member.hasBorrowedBook(bookId);
                
        } catch (BookNotFoundException | MemberNotFoundException e) {
            return false;
        }
    }
    
    /**
     * Get BookService reference
     * WHY? If other classes need book operations
     */
    public BookService getBookService() {
        return bookService;
    }
    
    /**
     * Get MemberService reference
     * WHY? If other classes need member operations
     */
    public MemberService getMemberService() {
        return memberService;
    }
}
