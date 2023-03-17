import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class Course {

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public UUID getUUID() {
        return uuid;
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

    public UUID getAuthorUUID() {
        return authorUUID;
    }

    public void setAuthorUUID(UUID authorUUID) {
        this.authorUUID = authorUUID;
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

    private String title;
    private String description;
    private UUID authorUUID;
    private ArrayList<Lesson> lessons;
    private int userProgress;
    private Language language;
    private final UUID uuid;
    private double finalGrade;

    public Course(String title, String description, UUID authorUUID, ArrayList<Lesson> lessons, int userProgress, Language language, double finalGrade) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.authorUUID = authorUUID;
        this.lessons = lessons;
        this.userProgress = userProgress;
        this.language = language;
        this.finalGrade = finalGrade;
    }

    public Course(String title, String description, UUID authorUUID, ArrayList<Lesson> lessons, int userProgress, Language language, UUID uuid, double finalGrade) {
        this.title = title;
        this.description = description;
        this.authorUUID = authorUUID;
        this.lessons = lessons;
        this.userProgress = userProgress;
        this.language = language;
        this.uuid = uuid;
        this.finalGrade = finalGrade;
    }

    public JSONObject getUserCourseData() {
        JSONObject stuff = new JSONObject();
        stuff.put("courseTitle", title);
        stuff.put("description", description);
        stuff.put("authorUUID", authorUUID.toString()); // FIX
        stuff.put("language", language.toString());
        stuff.put("lessons", null); // TODO properly implement jsonaware instead of this trash
        return stuff;
    }

    /**
     * @return returns an arraylist of a student's grades in each lesson (this is in the same order as the lesson arraylist)
     */
    public ArrayList<Double> fetchGrades() {
        ArrayList<Double> grades = new ArrayList<>();
        for (var l : lessons) grades.add(l.getGrade());
        return grades;
    }

    private void appendToStringBuilderJSONStyle(String key, String value, StringBuilder sb) {
        // looks like "key":"value"
        sb.append('"');
        sb.append(key);
        sb.append('"');
        sb.append(":");
        sb.append('"');
        sb.append(value);
        sb.append('"');
    }

}
