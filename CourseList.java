import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class CourseList {
    private static CourseList instance;
    private final ArrayList<Course> courseList;

    private CourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public static CourseList getInstance() {
        if (instance == null) instance = new CourseList(DataLoader.getCourses());
        return instance;
    }

    public Course getCourse(String title) {
        for (Course course : courseList) {
            if (Objects.equals(course.getTitle(), title)) return course;
        }
        return null;
    }

    public Course getCourse(UUID uuid) {
        for (Course course : courseList) {
            if (course.getUUID() == uuid) return course;
        }
        return null;
    }
}
