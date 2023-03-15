import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private String title;
    private String description;
    private Teacher author;
    private ArrayList<Lesson> lessons;
    private ArrayList<Comment> comments;
    private int userProgress;
    private Language language;
    private UUID uuid;
    private double finalGrade;

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Course(String title, Teacher author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getAuthor() {
        return author;
    }

    public void setAuthor(Teacher author) {
        this.author = author;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(int userProgress) {
        this.userProgress = userProgress;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public double getProgress(double userProgress) {
        return userProgress / lessons.size();
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Double> fetchGrades() {
        ArrayList<Double> grades = new ArrayList<>();
        for (var l : lessons) grades.add(l.getGrade());
        return grades;
    }

}
