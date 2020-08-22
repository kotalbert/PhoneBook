package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Defines what is to be looked for and stores results and time taken.
 *
 * Elapsed time is calculated from initialization, but can be reset to now().
 */
class SearchTask {

    private final List<String> queries;
    private int numberFound;
    private Instant start;
    private Duration elapsed;

    private SearchTask() {
        queries = new ArrayList<>();
        start = Instant.now();
    }

    private SearchTask(String fileName) {
        this();
        Path path = Path.of(PhoneBook.RESOURCES.toString(), fileName);
        populateQueries(path);
    }

    public static SearchTask makeTestSearchTask() {
        return new SearchTask("/find-short.txt");
    }

    public static SearchTask makeSearchTask() {
        return new SearchTask("/find.txt");
    }

    public void resetTimer() {
        start = Instant.now();
    }

    void timeStop() {
        elapsed = Duration.between(start, Instant.now());
    }

    List<String> getQueries() {
        return queries;
    }

    private void populateQueries(Path path) {
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(queries::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Found %d/%d entries. Time taken: %d min. %d sec. %d ms.",
                numberFound,
                queries.size(),
                elapsed.toMinutesPart(),
                elapsed.toSecondsPart(),
                elapsed.toMillisPart()
        );

    }
    void foundOne() {
        numberFound++;
    }

    public static void main(String[] args) {
        SearchTask st = SearchTask.makeTestSearchTask();
        System.out.println(st);
    }
}
