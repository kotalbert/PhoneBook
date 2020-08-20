package phonebook;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PhoneBook pb = PhoneBook.makePhonebook();
        SearchTask task = SearchTask.makeSearchTask(pb);
        System.out.println("Start searching...");
        task.search();
        System.out .println(task);


    }
}