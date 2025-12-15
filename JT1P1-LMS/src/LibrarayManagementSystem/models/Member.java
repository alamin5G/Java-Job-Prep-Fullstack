package LibrarayManagementSystem.models;

import LibrarayManagementSystem.exception.MemberNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Member {

    private Long memberId = 0L;
    private String memberName;
    private String memberPhone;
    private List<Integer> borrowedBooks;


    public Member(String memberName, String memberPhone) {

        this.memberName = memberName;
        this.memberPhone = memberPhone;
        ++memberId;
        new ArrayList<Integer>();

    }



    public void getMemberDetails() {
        try{
            System.out.print("Member ID: " + getMemberId());
            System.out.print("\tMember Name: " + getMemberByName());

            System.out.print("\tMember Phone: " + getMemberPhone());
            System.out.println("\tBooks Borrowed: " + getBooksBorrowed()+"\n");
        }catch (MemberNotFoundException e){
            System.err.println(e.getMessage() + " in Member class at getMemberDetails() method.");
        }
    }

    public void getBorrowedBookDetails(List<Integer> borrowedBooks) {
        for(Integer borrowedBook : borrowedBooks){
            System.out.println( "Book Id: " +borrowedBook);

        }
    }

    //getter setter

    public Long getMemberId() throws MemberNotFoundException {
        if (memberId == null || memberId <= 0) {
            throw new MemberNotFoundException("Member ID is not set or invalid.");
        }
        return memberId;
    }


    public String getMemberByName() throws MemberNotFoundException {
        if (memberName == null || memberName.isEmpty()) {
            throw new MemberNotFoundException("Member name is not set.");
        }
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

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void setBooksBorrowed(int booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }

}
