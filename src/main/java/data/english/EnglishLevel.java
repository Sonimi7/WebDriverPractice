package data.english;

public enum EnglishLevel implements IEnglishLevel{


    BEGINNER("Начальный уровень (Beginner)");

    private String nameLevelEnglish;
    EnglishLevel(String nameLevelEnglish) {
        this.nameLevelEnglish = nameLevelEnglish;
    }

    @Override
    public String getEnglishLevel() {
        return nameLevelEnglish;
    }

}
