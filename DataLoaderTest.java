public class DataLoaderTest {
    public static void main(String[] args) {
        UserData userData = UserData.getInstance();
        User user = userData.getUser("username");
        System.out.println(user);
        CourseData courseData = CourseData.getInstance();
        Course course = courseData.getCourse("CourseTitle");
        System.out.println(course);
        UserCourseData userCourseData = UserCourseData.getInstance();
        UserCourse userCourse = userCourseData.getUserCourseData(user.getUUID(), course.getUUID());
        System.out.println(userCourse);
    }
}
