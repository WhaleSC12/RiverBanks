import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Locale;

public class RegisteredUser {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected LocalDate dateOfBirth;
    protected String phoneNumber;
    protected String UID;
    protected String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth.toString();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<AbstractMap.SimpleEntry<Course, Integer>> getCourses() {
        return courses;
    }

    public String getUID() {
        return UID;
    }

    protected String password;
    // <Course, Completed Lessons>
    private ArrayList<AbstractMap.SimpleEntry<Course, Integer>> courses;
    DateTimeFormatter MDY_formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);

    public RegisteredUser(String firstName, String lastName, String email, String dateOfBirth, String phoneNumber, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, MDY_formatter);
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    } //TODO implement sanity checks

    public void joinCourse(Course course) {

    }

    public double getCourseProgress(Course course) {
        return 0; // TODO: much wow, very yes
    }
}