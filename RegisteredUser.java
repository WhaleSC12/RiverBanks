import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;

public class RegisteredUser {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected Date dateOfBirth;
    protected String phoneNumber;
    protected String UID;
    protected String username;
    protected String password;
    // <Course, Completed Lessons>
    private ArrayList<AbstractMap.SimpleEntry<Course,Integer>> courses;

    public RegisteredUser(String firstName, String lastName, String email, Date dateOfBirth, String phoneNumber, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    } //TODO implement sanity checks
    public void joinCourse(Course course){

    }
    public double getCourseProgress(Course course){
        return 0; // TODO: much wow, very yes
    }
}
