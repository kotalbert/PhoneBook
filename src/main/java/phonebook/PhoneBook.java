package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Container class for reading and holding the directory database.
 */
public class PhoneBook {

    public final static Path RESOURCES = Path.of("/home/pd/", "IdeaProjects/jba_resources/Phone_Book");
    private final List<DirectoryEntry> directory;

    private PhoneBook() {
        this.directory = new ArrayList<>();
    }

    private PhoneBook(String fileName) {
        this();
        Path path = Path.of(RESOURCES.toString(), fileName);
        populateDirectory(path);
    }

    public static PhoneBook makeTestPhonebook() {
        return new PhoneBook("directory-short.txt");
    }

    public static PhoneBook makePhonebook() {
        return new PhoneBook("directory.txt");
    }

    public static void main(String[] args) {
        PhoneBook pb = PhoneBook.makeTestPhonebook();
        SearchTask searchTask = SearchTask.makeTestSearchTask();
        pb.search(searchTask);
        System.out.println(searchTask);
    }

    private void populateDirectory(Path path) {
        try (Stream<String> stream = Files.lines(path)) {
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

    /**
     * Search current PhoneBook and update SearchTask instance with results and time taken.
     */
    public void search(SearchTask searchTask) {
        // elapsed time starts after searchTask initialization
        for (String query : searchTask.getQueries()) {
            if (isPresent(query)) {
                searchTask.foundOne();
            }
        }
        searchTask.timeStop();

    }

    private boolean isPresent(String query) {
        for (DirectoryEntry de : directory) {
            if (de.name.equals(query))
                return true;
        }
        return false;
    }
}


