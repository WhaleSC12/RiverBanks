public class DataLoaderTest {
    public static void main(String[] args) {
        UserList userList = UserList.getInstance();
        User user = userList.getUser("username");
        System.out.println(user);
        CourseList courseList = CourseList.getInstance();
        Course course = courseList.getCourse("CourseTitle");
        System.out.println(course);
    }
}
