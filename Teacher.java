import java.util.ArrayList;
import java.util.Date;

public class Teacher extends RegisteredUser {

    private ArrayList<Course> courseList;

    public Teacher(String firstName, String lastName, String email, String dateOfBirth, String phoneNumber, String username, String password) {
        super(firstName, lastName, email, dateOfBirth, phoneNumber, username, password);
    }

    public void addCourse(Course course) {

    }
}
