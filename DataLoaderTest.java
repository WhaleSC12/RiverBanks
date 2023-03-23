public class DataLoaderTest {
    public static void main(String[] args) {
//        UserData userData = UserData.getInstance();
//        User user = userData.getUser("username");
//        System.out.println(user);
//        CourseData courseData = CourseData.getInstance();
//        Course course = courseData.getCourse("CourseTitle");
//        System.out.println(course);
//        UserCourseData userCourseData = UserCourseData.getInstance();
//        UserCourse userCourse = userCourseData.getUserCourseData(user.getUUID(), course.getUUID());
//        System.out.println(userCourse);
//        UserData userData = UserData.getInstance();
//        UserCourseData userCourseData = UserCourseData.getInstance();
//        CourseData courseData = CourseData.getInstance();
//        userData.getUser(UUID.fromString("f025f1f5-7697-4c64-beaa-dec0ddde9359")).setEmail("totally an email");
//        DataWriter.saveAll();
        UserCourseData userCourseData = UserCourseData.getInstance();
        DataWriter.writeUserCourseData(userCourseData.userCourseData);
//        DataWriter.writeUserData(userData.userData.get(0));
//        User user1 = userData.userData.get(0);
//        user1.setEmail("emailtest11");
//        User user2 = userData.userData.get(1);
//        user2.setEmail("emailtest22");
//        try (JSONWriter jsonWriter = new JSONWriter("json/dat/users.json")) {
//            jsonWriter
//                    .atKey(user1.getUUID().toString())
//                    .data(user1);
//            jsonWriter
//                    .atKey(user2.getUUID().toString())
//                    .data(user2);
//            jsonWriter.write();
//        } catch (ParseException | IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
