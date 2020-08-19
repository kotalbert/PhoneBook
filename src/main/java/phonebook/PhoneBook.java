package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Container class for reading and holding the directory database.
 */
public class PhoneBook {

    private final List<DirectoryEntry> directory;

    private PhoneBook() {
        this.directory = new ArrayList<>();
    }

    private PhoneBook(String phoneDirectoryFilename) {
        this();
        populateDirectory(phoneDirectoryFilename);
    }

    public static PhoneBook makeTestPhonebook() {
        return new PhoneBook("/directory-short.txt");
    }

    private void populateDirectory(String phoneDirectoryFilename) {
        Path p = Path.of(getClass().getResource(phoneDirectoryFilename).getPath());
        try (Stream<String> stream = Files.lines(p)) {
            stream.forEach(this::addToDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToDirectory(String line) {
        Scanner scanner = new Scanner(line);
        int number = scanner.nextInt();
        String name = scanner.nextLine().strip();
        directory.add(new DirectoryEntry(number, name));
    }

    @Override
    public String toString() {
        return "PhoneBook{\n" +
                getDirectoryHead(10) + "}";
    }

    private String getDirectoryHead(int n) {
        StringBuilder sb = new StringBuilder();
        int c = 0;
        for (DirectoryEntry de : directory) {
            sb.append("\t").append(de).append("\n");
            c++;
            if (c == n)
                break;
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        PhoneBook pb = PhoneBook.makeTestPhonebook();
        System.out.println(pb);
    }

    public boolean isPresent(String query) {
        for (DirectoryEntry de : directory) {
            if (de.name.equals(query))
                return true;
        }
        return false;
    }
}


