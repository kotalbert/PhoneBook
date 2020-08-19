package phonebook;

class DirectoryEntry {
    public final int number;
    public final String name;

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
