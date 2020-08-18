package phonebook;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PhoneBook pb = PhoneBook.makeTestPhonebook();
        System.out.println("Start searching...");
    }
}