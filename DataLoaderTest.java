public class DataLoaderTest {
    public static void main(String[] args) {
        User testUser = DataLoader.getUser("username");
        System.out.println(testUser.toString());
    }
}
