package testDruv;
import src.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDataTest { 

    @BeforeClass
    public static void oneTimeSetup()
    { 

    }

    @AfterClass
    public void oneTimeTearDown() { 

    }

    @BeforeEach
    public void setup(){ 

    }

    @AfterEach
    public void tearDown()
    { 

    }

    @Test
    public void testSearchUserName() {
        UserData data = UserData.getInstance();

       User user1 = new User("JoneTest1","Test123", "Jone","Bell", "JoneBell212@gmail.com", "8633636514", "Teacher");
       data.userList.add(user1);

      User user2 = new User("JaneHill14","Hill1414", "Jane","Hill", "HillJ45@gmail.com", "7049053356", "Teacher");
      data.userList.add(user2);
      assertEquals(user1, data.getUser("JoneTest1"));
    }

    @Test
    public void testSearchUserUUID() {
        UserData data = UserData.getInstance();

        User user1 = new User("JoneTest1","Test123", "Jone","Bell", "JoneBell212@gmail.com", "8633636514", "Teacher");
        data.userList.add(user1);
        
        User user2 = new User("JaneHill14","Hill1414", "Jane","Hill", "HillJ45@gmail.com", "7049053356", "Teacher");
        data.userList.add(user2);
       assertEquals(user1, data.getUser("JoneTest1"));
    }

    @Test
    public void testUserSearch(){ 
      UserData data = UserData.getInstance();
      data = UserData.getInstance();
    
      User user1 = new User("JoneTest1","Test123", "Jone","Bell", "JoneBell212@gmail.com", "8633636514", "Teacher");
      data.userList.add(user1);
      
      User user2 = new User("JaneHill14","Hill1414", "Jane","Hill", "HillJ45@gmail.com", "7049053356", "Teacher");
      data.userList.add(user2); 
      User test = user2;
      assertEquals(data.getUser("JaneHill14"), test);
    }
}  