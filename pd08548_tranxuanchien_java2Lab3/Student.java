package mob1023_lab03;

public class Student {
    private String name;
    private Double score;
    private String major;
    private String grade;
    private boolean gift;

    public Student(String name, Double score, String major, String grade, boolean gift) {
        this.name = name;
        this.score = score;
        this.major = major;
        this.grade = grade;
        this.gift = gift;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isGift() {
        return gift;
    }

    public void setGift(boolean gift) {
        this.gift = gift;
    }
}
