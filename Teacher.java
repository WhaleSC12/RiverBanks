import java.util.ArrayList;

public class Teacher extends User {

    private ArrayList<Course> courseList;

    public Teacher(String firstName, String lastName, String email, String dateOfBirth, String phoneNumber, String username, String password) {
        super(firstName, lastName, email, dateOfBirth, phoneNumber, username, password);
    }

    public void addCourse(Course course) {

    }
}
