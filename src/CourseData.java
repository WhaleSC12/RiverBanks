package src;

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

    /**
     * @return an instance of the class, rather than the inner arraylist for the purpose of implementing helper functions
     * to prevent the need to change multiple files should something change
     */
    public static CourseData getInstance() {
        if (instance == null) instance = new CourseData(DataLoader.getCourses("json/dat/courses.json"));
        return instance;
    }

    /**
     * returns a course matching the title, note that this may be insufficient if multiple courses share a title
     *
     * @param title title of the course being searched for
     * @return the course matching the title, else null
     */
    public Course getCourse(String title) {
        for (Course course : courseList) {
            if (Objects.equals(course.getTitle(), title)) return course;
        }
        return null;
    }

    /**
     * finds the course matching the uuid and returns it
     *
     * @param uuid the uuid of the course being searched for
     * @return course object matching the uuid
     */
    public Course getCourse(UUID uuid) {
        for (Course course : courseList) {
            if (course.getUUID().equals(uuid)) return course;
        }
        return null;
    }
}
