package LibrarayManagementSystem.repository;

import LibrarayManagementSystem.models.Book;
import LibrarayManagementSystem.models.Member;

import java.io.*;
import java.util.HashMap;

/**
 * FileRepository - Handles file persistence for Library Management System
 * 
 * PURPOSE:
 * --------
 * Save and load data to/from files so data persists between program runs
 * 
 * WHAT WE SAVE:
 * -------------
 * 1. Books data → books.dat
 * 2. Members data → members.dat
 * 
 * HOW IT WORKS:
 * -------------
 * - Uses Java Serialization (ObjectOutputStream/ObjectInputStream)
 * - Saves HashMap directly to file
 * - Loads HashMap back from file
 * 
 * WHEN TO USE:
 * ------------
 * - saveBooks() → After adding/removing books
 * - saveMembers() → After registering/removing members
 * - loadBooks() → On program start
 * - loadMembers() → On program start
 */
public class FileRepository {

    // File paths
    private static final String BOOKS_FILE = "data/books.dat";
    private static final String MEMBERS_FILE = "data/members.dat";
    private static final String DATA_DIR = "data";

    /**
     * Constructor - Creates data directory if it doesn't exist
     */
    public FileRepository() {
        createDataDirectory();
    }

    /**
     * Create data directory
     */
    private void createDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("📁 Created data directory");
        }
    }

    // ==================== BOOK OPERATIONS ====================

    /**
     * Save books to file
     * 
     * @param books HashMap of books to save
     */
    public void saveBooks(HashMap<Long, Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(BOOKS_FILE))) {

            oos.writeObject(books);
            System.out.println("💾 Books saved successfully (" + books.size() + " books)");

        } catch (IOException e) {
            System.err.println("❌ Error saving books: " + e.getMessage());
        }
    }

    /**
     * Load books from file
     * 
     * @return HashMap of books, or empty HashMap if file doesn't exist
     */
    @SuppressWarnings("unchecked")
    public HashMap<Long, Book> loadBooks() {
        File file = new File(BOOKS_FILE);

        // If file doesn't exist, return empty HashMap
        if (!file.exists()) {
            System.out.println("📂 No saved books found. Starting fresh.");
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(BOOKS_FILE))) {

            HashMap<Long, Book> books = (HashMap<Long, Book>) ois.readObject();
            System.out.println("📖 Loaded " + books.size() + " books from file");
            return books;

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error loading books: " + e.getMessage());
            return new HashMap<>();
        }
    }

    // ==================== MEMBER OPERATIONS ====================

    /**
     * Save members to file
     * 
     * @param members HashMap of members to save
     */
    public void saveMembers(HashMap<Long, Member> members) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(MEMBERS_FILE))) {

            oos.writeObject(members);
            System.out.println("💾 Members saved successfully (" + members.size() + " members)");

        } catch (IOException e) {
            System.err.println("❌ Error saving members: " + e.getMessage());
        }
    }

    /**
     * Load members from file
     * 
     * @return HashMap of members, or empty HashMap if file doesn't exist
     */
    @SuppressWarnings("unchecked")
    public HashMap<Long, Member> loadMembers() {
        File file = new File(MEMBERS_FILE);

        // If file doesn't exist, return empty HashMap
        if (!file.exists()) {
            System.out.println("📂 No saved members found. Starting fresh.");
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(MEMBERS_FILE))) {

            HashMap<Long, Member> members = (HashMap<Long, Member>) ois.readObject();
            System.out.println("👥 Loaded " + members.size() + " members from file");
            return members;

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error loading members: " + e.getMessage());
            return new HashMap<>();
        }
    }

    // ==================== UTILITY OPERATIONS ====================

    /**
     * Clear all saved data (delete files)
     */
    public void clearAllData() {
        File booksFile = new File(BOOKS_FILE);
        File membersFile = new File(MEMBERS_FILE);

        if (booksFile.exists()) {
            booksFile.delete();
            System.out.println("🗑️ Deleted books data");
        }

        if (membersFile.exists()) {
            membersFile.delete();
            System.out.println("🗑️ Deleted members data");
        }
    }

    /**
     * Check if saved data exists
     */
    public boolean hasSavedData() {
        File booksFile = new File(BOOKS_FILE);
        File membersFile = new File(MEMBERS_FILE);
        return booksFile.exists() || membersFile.exists();
    }
}
