package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Defines what is to be looked for and stores results and time taken.
 */
class SearchTask {

    private final List<String> queries;
    private int numberFound;
    private long millisTaken;

    private SearchTask() {
        queries = new ArrayList<>();
    }

    private SearchTask(String searchTaskFileName) {
        this();
        populateQueries(searchTaskFileName);
    }

    public static SearchTask makeTestSearchTask() {
        return new SearchTask("/find-short.txt");
    }

    public static SearchTask makeSearchTask() {
        return new SearchTask("/find.txt");
    }

    public static void main(String[] args) {
        SearchTask st = SearchTask.makeTestSearchTask();
        System.out.println(st);
    }

    private void populateQueries(String searchTaskFileName) {
        Path p = Path.of(getClass().getResource(searchTaskFileName).getPath());
        try (Stream<String> stream = Files.lines(p)) {
            stream.forEach(queries::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
