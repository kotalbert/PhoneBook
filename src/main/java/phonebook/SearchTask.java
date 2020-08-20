package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Encapsulation of search algorithm as well as input search criteria.
 */
class SearchTask {

    private final List<String> searchTask;
    private PhoneBook phoneBook;
    private int numberFound;
    private long millisTaken;

    private SearchTask(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        searchTask = new ArrayList<>();
    }

    private SearchTask(PhoneBook phoneBook, String searchTaskFileName) {
        this(phoneBook);
        populateSearchTask(searchTaskFileName);
    }

    public static SearchTask makeTestSearchTask() {
        return new SearchTask(PhoneBook.makeTestPhonebook(), "/find-short.txt");
    }

    public static SearchTask makeSearchTask(PhoneBook phoneBook) {
        return new SearchTask(phoneBook, "/find.txt");
    }

    public static void main(String[] args) {
        SearchTask st = SearchTask.makeTestSearchTask();
        st.search();
        System.out.println(st);
    }

    private void populateSearchTask(String searchTaskFileName) {
        Path p = Path.of(getClass().getResource(searchTaskFileName).getPath());
        try (Stream<String> stream = Files.lines(p)) {
            stream.forEach(searchTask::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actual implementation of search algorithm.
     */
    public void search() {
        long timeStart = System.currentTimeMillis();
        for (String query : searchTask) {
            if (phoneBook.isPresent(query)) {
                numberFound++;
            }
            if (numberFound == searchTask.size())
                break;
        }
        millisTaken = System.currentTimeMillis() - timeStart;

    }

    private int seconds() {
        return (int) (millisTaken / 1000) % 60;
    }

    private int minutes() {
        return (int) ((millisTaken / (1000 * 60)) % 60);
    }

    @Override
    public String toString() {
        return "SearchTask{" +
                "numberFound=" + numberFound +
                ", millisTaken=" + millisTaken +
                ", minutes=" + minutes() +
                ", seconds=" + seconds() +
                '}';
    }

}
