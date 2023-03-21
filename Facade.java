import java.util.UUID;

//Where all the method will go that will be used in the LearningUI 
public class Facade {
    private User user;
    private Course course;
    private Lesson lesson;
    private Test test;
    private Question question;

    public void Login(UUID uuid, String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance) 
    {
      user.User(uuid,username,password,firstName, lastName, email, phoneNumber, clearance);
    }


    public void createLogin(UUID uuid, String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance)
    { 
      user.User(username,password,firstName, lastName, email, phoneNumber, clearance);
    }

  /* search function that searches by name of course
   * can be modified if needed to 
   */
   public boolean searchCourse(String courseName)
   { 
     return course.searchCourse(courseName);
   }

    public void createCourse() 
    { 

    }
/* 
 * sets all parameters to null and logs the user out
 * @return null
*/
    public void Logout() 
    { 
      user.setUsername(null);
      user.setPassword(null);
      user.setFirstName(null);
      user.setLastName(null);
      user.setEmail(null);
      user.setPhoneNumber(null);
      user.setClearance(null);
    }


}
