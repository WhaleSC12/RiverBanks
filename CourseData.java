import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class CourseData {
    private static CourseData instance;
    public final ArrayList<Course> courseData;

    private CourseData(ArrayList<Course> courseData) {
        this.courseData = courseData;
    }

    public static CourseData getInstance() {
        if (instance == null) instance = new CourseData(DataLoader.getCourses());
        return instance;
    }

    public Course getCourse(String title) {
        for (Course course : courseData) {
            if (Objects.equals(course.getTitle(), title)) return course;
        }
        return null;
    }

    public Course getCourse(UUID uuid) {
        for (Course course : courseData) {
            if (course.getUUID() == uuid) return course;
        }
        return null;
    }
}
