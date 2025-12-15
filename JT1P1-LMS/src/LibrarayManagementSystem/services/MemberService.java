package LibrarayManagementSystem.services;

import LibrarayManagementSystem.exception.MemberNotFoundException;
import LibrarayManagementSystem.models.Member;
import LibrarayManagementSystem.models.Book;
import LibrarayManagementSystem.repository.FileRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * MemberService - Manages all member-related operations
 * 
 * SOLID Principle: Single Responsibility
 * - This class ONLY handles Member operations
 * - Does NOT handle books or library logic
 * 
 * NEW: File Persistence
 * - Auto-saves after add/remove operations
 * - Can load data from file on startup
 */
public class MemberService {

    private HashMap<Long, Member> members;
    private FileRepository fileRepository;

    public MemberService() {
        this.members = new HashMap<>();
        this.fileRepository = new FileRepository();
    }

    /**
     * Constructor with FileRepository injection
     */
    public MemberService(FileRepository fileRepository) {
        this.members = new HashMap<>();
        this.fileRepository = fileRepository;
    }

    /**
     * Load members from file
     */
    public void loadFromFile() {
        this.members = fileRepository.loadMembers();
    }

    /**
     * Save members to file
     */
    public void saveToFile() {
        fileRepository.saveMembers(members);
    }

    /**
     * Register a new member
     */
    public void registerMember(Member member) {
        try {
            members.put(member.getMemberId(), member);
            System.out.println("✅ Member registered: " + member.getMemberName());
            saveToFile(); // Auto-save
        } catch (MemberNotFoundException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Find member by ID
     * 
     * @throws MemberNotFoundException if member doesn't exist
     */
    public Member findMemberById(long memberId) throws MemberNotFoundException {
        Member member = members.get(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member with ID " + memberId + " not found");
        }
        return member;
    }

    /**
     * Get all members
     */
    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    /**
     * Remove member
     */
    public void removeMember(long memberId) throws MemberNotFoundException {
        Member member = findMemberById(memberId);
        members.remove(memberId);
        System.out.println("✅ Member removed: " + member.getMemberName());
        saveToFile(); // Auto-save
    }

    /**
     * Display all members
     */
    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        System.out.println("\n📋 === All Members ===");
        for (Member member : members.values()) {
            System.out.println(member);
        }
        System.out.println("=".repeat(50));
    }

    /**
     * Display member's borrowed books with full book details
     * NOTE: Needs BookService to get book details
     */
    public void displayMemberBorrowedBooks(long memberId, BookService bookService) {
        try {
            Member member = findMemberById(memberId);
            List<Long> borrowedBookIds = member.getBorrowedBookIds();

            System.out.println("\n📚 Books borrowed by: " + member.getMemberName());

            if (borrowedBookIds.isEmpty()) {
                System.out.println("   No books borrowed.");
                return;
            }

            for (Long bookId : borrowedBookIds) {
                try {
                    Book book = bookService.findBookById(bookId);
                    System.out.println("   - " + book.getBookTitle() + " by " + book.getBookAuthor());
                } catch (Exception e) {
                    System.out.println("   - Book ID " + bookId + " (details not found)");
                }
            }

        } catch (MemberNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    /**
     * Get total members count
     */
    public int getTotalMembersCount() {
        return members.size();
    }

    /**
     * Get members HashMap (for direct access if needed)
     */
    public HashMap<Long, Member> getMembers() {
        return members;
    }
}
