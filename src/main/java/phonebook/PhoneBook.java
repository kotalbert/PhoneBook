package phonebook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class PhoneBook {
    // construct with list to find
    // traverse database once
    // keep track if database already searched
    // search mehtod
    // number of found property
    // number on the list

    private final Map<String, Integer> directory; // name - number directory

    private PhoneBook() {
        this.directory = new HashMap<>();
    }

    private PhoneBook(String phoneDirectoryFilename) throws IOException {
        this();
        populateDirectory(phoneDirectoryFilename);
    }

    private void populateDirectory(String phoneDirectoryFilename) throws IOException {
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
        String name = scanner.nextLine();
        directory.put(name, number);
    }

    public static PhoneBook makeTestPhonebook() throws IOException {
        return new PhoneBook("/directory-short.txt");
    }

    private void showDirectoryHead(int n) {
        int c = 0;
        for (String k : directory.keySet()) {
            System.out.println(k + " " + directory.get(k));
            c++;
            if (c == n)
                return;
        }
    }



    public static void main(String[] args) throws IOException {
        PhoneBook pb = PhoneBook.makeTestPhonebook();
        pb.showDirectoryHead(2);
    }


}
