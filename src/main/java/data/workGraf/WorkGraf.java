package data.workGraf;

public enum WorkGraf {

    REMOTELY("Удаленно");

    private String name;

    WorkGraf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
