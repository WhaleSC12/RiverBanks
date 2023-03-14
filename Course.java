import java.util.ArrayList;

public class Course {
    private String title;
    private String description;
    private Teacher author;
    private ArrayList<Lesson> lessons;
    private ArrayList<Comment> comments;
    private int userProgress;
    private Language language;
    private String UID;

    public Course(String title, Teacher author) {
        this.title = title;
        this.author = author;
    }

    public double getProgress(){

    }
    public ArrayList<Comment> getComments(){

    }

}
