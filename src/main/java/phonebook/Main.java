package phonebook;

public class Main {
    public static void main(String[] args) {
        SearchTask task = SearchTask.makeSearchTask();
        PhoneBook pb = PhoneBook.makePhonebook();
        System.out.println("Start searching...");
        pb.search(task);
        System.out.println(task);


    }
}