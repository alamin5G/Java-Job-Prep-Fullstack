package LibrarayManagementSystem.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import LibrarayManagementSystem.exception.MemberNotFoundException;

public class Member implements Serializable {

    private static final long serialVersionUID = 1L; // For version control

    private static long memberIdCounter = 0L;
    private long memberId;
    private String memberName;
    private String memberPhone;
    private List<Long> borrowedBookIds;

    // Default constructor
    public Member() {
    }

    public Member(String memberName, String memberPhone) {
        this.memberId = ++memberIdCounter;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.borrowedBookIds = new ArrayList<>();
    }

    // getter setter
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

    // Borrowed books id list
    public List<Long> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    /**
     * Add a book ID to member's borrowed list
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
