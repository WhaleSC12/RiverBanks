public class DataLoaderTest {
    public static void main(String[] args) {
        User testUser = DataLoader.UserData.getUser("username");
        System.out.println(testUser.toString());
    }
}
