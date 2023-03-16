import java.util.UUID;

//Where all the method will go that will be used in the LearningUI 
public class Facade {
    private User user;
    private RegisteredUser registeredUser;
    private Teacher teacher;
    private Student student;
    private Course course;
    private Lesson lesson;
    private Test test;
    private Question question;

    public void studentLogin(String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance) {
          student.LoginStudent(username, password, firstName, lastName, email, phoneNumber, clearance);
    }

    public void createStudent(UUID uuid, String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance) {
        student.createStudent(uuid, username, password, firstName, lastName, email, phoneNumber, clearance);
  }


}
