package LibrarayManagementSystem.models;

import java.util.ArrayList;
import java.util.List;

import LibrarayManagementSystem.exception.MemberNotFoundException;

public class Member {

    private static long memberIdCounter = 0L;
    private long memberId;
    private String memberName;
    private String memberPhone;
    private List<Long> borrowedBookIds;  // Changed to Long to match Book ID type

    // Default constructor
    public Member() {
    }

    public Member(String memberName, String memberPhone) {
        this.memberId = ++memberIdCounter;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.borrowedBookIds = new ArrayList<>();  // ✅ Properly initialized
    }

    //getter setter
    public Long getMemberId() throws MemberNotFoundException {
        if (memberId <= 0) {
            throw new MemberNotFoundException("Member ID is not set or invalid.");
        }
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    // Borrowed books management
    public List<Long> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    /**
     * Add a book ID to member's borrowed list WHY HERE? Because this is
     * member's OWN data, member manages it
     */
    public void addBorrowedBook(long bookId) {
        if (!borrowedBookIds.contains(bookId)) {
            borrowedBookIds.add(bookId);
        }
    }

    /**
     * Remove a book ID from member's borrowed list
     */
    public void removeBorrowedBook(long bookId) {
        borrowedBookIds.remove(bookId);
    }

    /**
     * Check if member has borrowed a specific book
     */
    public boolean hasBorrowedBook(long bookId) {
        return borrowedBookIds.contains(bookId);
    }

    /**
     * Get count of books borrowed
     */
    public int getBorrowedBooksCount() {
        return borrowedBookIds.size();
    }

    @Override
    public String toString() {
        return "Member{"
                + "memberId=" + memberId
                + ", memberName='" + memberName + '\''
                + ", memberPhone='" + memberPhone + '\''
                + ", borrowedBooksCount=" + borrowedBookIds.size()
                + '}';
    }
}
