import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private String title;
    private String description;
    private Teacher author;
    private ArrayList<Lesson> lessons;
    private int userProgress;
    private Language language;
    private UUID uuid;

    public Course(String title, String description, Teacher author, ArrayList<Lesson> lessons, int userProgress, Language language, double finalGrade) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.author = author;
        this.lessons = lessons;
        this.userProgress = userProgress;
        this.language = language;
        this.finalGrade = finalGrade;
    }

    public Course(String title, String description, Teacher author, ArrayList<Lesson> lessons, int userProgress, Language language, UUID uuid, double finalGrade) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.lessons = lessons;
        this.userProgress = userProgress;
        this.language = language;
        this.uuid = uuid;
        this.finalGrade = finalGrade;
    }

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

    /**
     * @return returns an arraylist of a student's grades in each lesson (this is in the same order as the lesson arraylist)
     */
    public ArrayList<Double> fetchGrades() {
        ArrayList<Double> grades = new ArrayList<>();
        for (var l : lessons) grades.add(l.getGrade());
        return grades;
    }

}
