import java.util.UUID;

//Where all the method will go that will be used in the LearningUI 
public class Facade {
    private User user;

    private User registeredUser;
    private UserData currenUserData;
    private Course course;
    private Course.Lesson lesson;
    private Course.Lesson.Test test;
    private Course.Lesson.Test.Question question;


    /* 
     * still needs to be finished 
     */
    public void Login(String username, String password) 
    {
        currenUserData.getUser(username);
    }


    public void createLogin(UUID uuid, String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance) 
    {
        user = new User(username, password, firstName, lastName, email, phoneNumber, clearance);
    }

    /* search function that searches by name of course
     * can be modified if needed to
     */
    public boolean searchCourse(String courseName) 
    {
        return false;
    }
// will start working over weekend
    public void createCourse() 
    {
       /* work on using Course that holds all method 
        * for creating a courses, i.e, lessons, test and questions
        */
    }

    /*
     * sets all parameters to null and logs the user out
     * @return null
     */
    public void Logout() {
        user.setUsername(null);
        user.setPassword(null);
        user.setFirstName(null);
        user.setLastName(null);
        user.setEmail(null);
        user.setPhoneNumber(null);
        user.setClearance(null);
    }


}
