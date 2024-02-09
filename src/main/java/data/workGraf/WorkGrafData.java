package data.workGraf;

public enum WorkGrafData {

    REMOTELY("Полный день"),
    FLEXIBLE("Гибкий график"),
    REMOTE("Удаленно");

    private String name;

    WorkGrafData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
