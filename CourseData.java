import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * Singleton containing a list of all courses
 */
public class CourseData {
    private static CourseData instance;
    public final ArrayList<Course> courseList;

    private CourseData(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public static CourseData getInstance() {
        if (instance == null) instance = new CourseData(DataLoader.getCourses());
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
