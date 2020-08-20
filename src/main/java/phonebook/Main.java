package phonebook;

public class Main {
    public static void main(String[] args) {
        PhoneBook pb = PhoneBook.makePhonebook();
        SearchTask task = SearchTask.makeSearchTask();
        System.out.println("Start searching...");
        System.out .println(task);


    }
}