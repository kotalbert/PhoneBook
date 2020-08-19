package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class SearchTask {

    private final List<String> searchTask;
    private int numberFound;
    private long millisTaken;
    
    private SearchTask() {
        searchTask = new ArrayList<>();
    }
    private SearchTask(String searchTaskFileName) {
        this();
        populateSearchTask(searchTaskFileName);
    }

    public static SearchTask createTestSearchTask() {
        return new SearchTask("/find-short.txt");
    }

    private void populateSearchTask(String searchTaskFileName) {
        Path p = Path.of(getClass().getResource(searchTaskFileName).getPath());
        try (Stream<String> stream = Files.lines(p)) {
            stream.forEach(searchTask::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "SearchTask{" +
                "searchTask=" + searchTask +
                ", numberFound=" + numberFound +
                ", millisTaken=" + millisTaken +
                '}';
    }

    public static void main(String[] args) {
        SearchTask st = SearchTask.createTestSearchTask();
        System.out.println(st);
    }

}
