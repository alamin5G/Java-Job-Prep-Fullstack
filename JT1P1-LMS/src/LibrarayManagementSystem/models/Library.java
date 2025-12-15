package LibrarayManagementSystem.models;

import LibrarayManagementSystem.exception.BookNotFoundException;
import LibrarayManagementSystem.exception.MemberNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Library {
    List<Book> books;
    HashMap<Long, Member> members;
    private UUID transactionId;



    public void addBook(Book book) {
        books.add(book);
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getBookTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByAuthor(String author) {
        for (Book book : books) {
            if (book.getBookAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }


    public void registerMember(Member member) {
       try{
           members.put(member.getMemberId(), member);

           } catch (MemberNotFoundException e) {
              System.err.println(e.getMessage());
       }
    }

    public Member getMemberById(long memberId) {
        return members.get(memberId);
    }

    public void issueBook(long bookId, long memberId) {

    }



}
