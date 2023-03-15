import java.util.UUID;

public class Student extends User {
    // TODO: someone else do this i'm getting tired

    public Student(UUID uuid, String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance) {
        super(uuid, username, password, firstName, lastName, email, phoneNumber, clearance);
    }

    public Student(String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance) {
        super(username, password, firstName, lastName, email, phoneNumber, clearance);
    }
}
