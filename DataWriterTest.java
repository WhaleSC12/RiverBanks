import java.util.UUID;

public class DataWriterTest {
    public static void main(String[] args) {
        UUID testUUID = UUID.fromString("8c3eedf7-ae54-4543-b60e-30750467b1d1");
        User testUser = new User(testUUID, "username2",
                "password", "first name",
                "last name", "email",
                "phone number", "clearance");
        System.out.println(DataWriter.writeUserData(testUser));
    }
}
