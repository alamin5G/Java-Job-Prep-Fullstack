package LibrarayManagementSystem.models;

public class Library {

    private String libraryName;
    private String libraryAddress;

    public Library(String libraryName, String libraryAddress) {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + libraryName + '\'' +
                ", address='" + libraryAddress + '\'' +
                '}';
    }
}
