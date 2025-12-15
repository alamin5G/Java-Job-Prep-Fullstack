package LibrarayManagementSystem.services;

import LibrarayManagementSystem.exception.MemberNotFoundException;
import LibrarayManagementSystem.models.Member;
import LibrarayManagementSystem.models.Library;
import LibrarayManagementSystem.models.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * MemberService - Handles ALL Member-related Operations
 * 
 * RESPONSIBILITY:
 * - Member CRUD operations
 * - Member search operations
 * - Display member info
 * - Show member's borrowed books (coordination with BookService)
 * 
 * WHY SEPARATE FROM BookService?
 * -------------------------------
 * Same reason: Separation of Concerns!
 * - BookService = Book কাজ
 * - MemberService = Member কাজ
 * - LibraryService = দুইটাকে coordinate করে
 */
public class MemberService {

    private Library library;  // Library থেকে members access করবে
    
    public MemberService(Library library) {
        this.library = library;
    }
    
    // ==================== CREATE OPERATIONS ====================
    
    /**
     * Register new member
     * 
     * FLOW:
     * 1. Validate member
     * 2. Add to library's members list
     */
    public void registerMember(Member member) {
        if (member == null) {
            System.out.println("❌ Cannot register null member!");
            return;
        }
        
        library.getMembers().add(member);
        System.out.println("✅ Member registered: " + member.getMemberName());
    }
    
    // ==================== READ OPERATIONS ====================
    
    /**
     * Find member by ID
     * 
     * LOGIC: Loop করে ID match করো, না পেলে exception throw
     */
    public Member findMemberById(long memberId) throws MemberNotFoundException {
        for (Member member : library.getMembers()) {
            try {
                if (member.getMemberId() == memberId) {
                    return member;
                }
            } catch (MemberNotFoundException e) {
                continue;
            }
        }
        throw new MemberNotFoundException("Member not found with ID: " + memberId);
    }
    
    /**
     * Find member by name (partial match)
     */
    public List<Member> findMembersByName(String name) {
        List<Member> foundMembers = new ArrayList<>();
        
        for (Member member : library.getMembers()) {
            if (member.getMemberName() != null && 
                member.getMemberName().toLowerCase().contains(name.toLowerCase())) {
                foundMembers.add(member);
            }
        }
        
        return foundMembers;
    }
    
    /**
     * Get ALL members
     */
    public List<Member> getAllMembers() {
        return library.getMembers();
    }
    
    /**
     * Display member basic info
     */
    public void displayMemberDetails(Member member) {
        if (member == null) {
            System.out.println("❌ No member to display!");
            return;
        }
        
        try {
            System.out.println("\n👤 Member Details:");
            System.out.println("   ID: " + member.getMemberId());
            System.out.println("   Name: " + member.getMemberByName());
            System.out.println("   Phone: " + member.getMemberPhone());
            System.out.println("   Books Borrowed: " + member.getBorrowedBooksCount());
        } catch (MemberNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    /**
     * Display member এর borrowed books এর DETAILS
     * 
     * ⭐ KEY CONCEPT: এখানে BookService লাগবে!
     * 
     * WHY? 
     * - Member এর কাছে শুধু book IDs আছে
     * - Full book details পেতে হলে BookService এর help লাগবে
     * 
     * এইটাই হলো SERVICE COORDINATION!
     */
    public void displayMemberBorrowedBooks(long memberId, BookService bookService) {
        try {
            Member member = findMemberById(memberId);
            List<Long> bookIds = member.getBorrowedBookIds();
            
            if (bookIds.isEmpty()) {
                System.out.println("\n📚 " + member.getMemberByName() + " has not borrowed any books.");
                return;
            }
            
            System.out.println("\n📚 Books borrowed by " + member.getMemberByName() + ":");
            System.out.println("=".repeat(80));
            
            // For each book ID, get full book details from BookService
            for (Long bookId : bookIds) {
                try {
                    Book book = bookService.findBookById(bookId);
                    bookService.displayBookDetails(book);
                    System.out.println("-".repeat(80));
                } catch (Exception e) {
                    System.out.println("❌ Book with ID " + bookId + " not found!");
                }
            }
            
        } catch (MemberNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
    
    /**
     * Display all members
     */
    public void displayAllMembers() {
        List<Member> members = library.getMembers();
        
        if (members.isEmpty()) {
            System.out.println("\n👥 No members registered!");
            return;
        }
        
        System.out.println("\n👥 === All Library Members (" + members.size() + " total) ===");
        System.out.println("=".repeat(80));
        
        for (Member member : members) {
            displayMemberDetails(member);
            System.out.println("-".repeat(80));
        }
    }
    
    // ==================== UPDATE OPERATIONS ====================
    
    /**
     * Update member information
     */
    public void updateMember(long memberId, Member updatedInfo) throws MemberNotFoundException {
        Member existingMember = findMemberById(memberId);
        
        if (updatedInfo.getMemberName() != null) {
            existingMember.setMemberName(updatedInfo.getMemberName());
        }
        if (updatedInfo.getMemberPhone() != null) {
            existingMember.setMemberPhone(updatedInfo.getMemberPhone());
        }
        
        System.out.println("✅ Member updated successfully!");
    }
    
    // ==================== DELETE OPERATIONS ====================
    
    /**
     * Remove member from library
     * 
     * WARNING: Check if member has borrowed books first!
     */
    public void removeMember(long memberId) throws MemberNotFoundException {
        Member member = findMemberById(memberId);
        
        // Safety check
        if (member.getBorrowedBooksCount() > 0) {
            System.out.println("❌ Cannot remove member! " + member.getBorrowedBooksCount() + " books not returned yet.");
            return;
        }
        
        library.getMembers().remove(member);
        System.out.println("✅ Member removed: " + member.getMemberName());
    }
    
    // ==================== STATISTICS ====================
    
    /**
     * Get total members count
     */
    public int getTotalMembersCount() {
        return library.getMembers().size();
    }
}
