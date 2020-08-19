package phonebook;

class DirectoryEntry {
    private final int number;
    private final String name;

    DirectoryEntry(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DirectoryEntry{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
