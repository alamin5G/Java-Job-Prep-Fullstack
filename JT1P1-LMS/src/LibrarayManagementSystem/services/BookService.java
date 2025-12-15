package LibrarayManagementSystem.services;

import LibrarayManagementSystem.models.Book;

public class BookService {
    Book book;
    public BookService() {
        book = new Book();
    }


    public void getBookDetails(){
        System.out.print("Book ID: " + book.getBookId());
        System.out.print("\tBook Title: " + book.getBookTitle());
        System.out.print("\tBook Author: " + book.getBookAuthor());
        System.out.print("\tBook Publisher: " + book.getBookPublisher());
        System.out.print("\tBook ISBN: " + book.getBookISBN());
        System.out.print("\tBook Total Copies: " + book.getBookCopiesTotal());
        System.out.print("\tBook Available copies: " + book.getBookCopiesAvailable());
        System.out.println("\tBook Available: " + book.isBookAvailable() + "\n");
    }


}
