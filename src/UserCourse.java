package src;

import org.json.simple.JSONAware;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Data class which holds user data pertaining to courses
 */
public class UserCourse implements JSONAware {

    UUID userUUID;
    UUID courseUUID;
    int lessonsCompleted;
    ArrayList<Double> lessonGrades;

    public UserCourse(UUID userUUID, UUID courseUUID, int lessonsCompleted, ArrayList<Double> lessonGrades) {
        this.userUUID = userUUID;
        this.courseUUID = courseUUID;
        this.lessonsCompleted = lessonsCompleted;
        this.lessonGrades = lessonGrades;
    }

    public UUID getUserUUID() {
        return userUUID;
    }

    public UUID getCourseUUID() {
        return courseUUID;
    }

    public int getLessonsCompleted() {
        return lessonsCompleted;
    }

    public void setLessonsCompleted(int lessonsCompleted) {
        this.lessonsCompleted = lessonsCompleted;
    }

    public ArrayList<Double> getLessonGrades() {
        return lessonGrades;
    }

    public void setLessonGrades(ArrayList<Double> lessonGrades) {
        this.lessonGrades = lessonGrades;
    }

    @Override
    public String toString() {
        return "src.UserCourse{" +
                "userUUID=" + userUUID +
                ", courseUUID=" + courseUUID +
                ", lessonsCompleted=" + lessonsCompleted +
                ", lessonGrades=" + lessonGrades +
                '}';
    }

    private void appendToStringBuilderJSONStyle(String key, Object value, StringBuilder sb) {
        // looks like "key":"value"
        sb.append('"');
        sb.append(key);
        sb.append('"');
        sb.append(":");
        sb.append('"');
        sb.append(value);
        sb.append('"');
    }


    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append('"');
        sb.append("lessonsCompleted");
        sb.append('"');
        sb.append(":");
        sb.append(lessonsCompleted);
        sb.append(',');

        sb.append('"');
        sb.append("lessonGrades");
        sb.append('"');
        sb.append(":");
        sb.append('[');
        boolean first = true;
        for (double grade : lessonGrades) {
            if (!first) sb.append(",");
            else first = false;
            sb.append(grade);
        }
        sb.append(']');

        sb.append('}');

        return sb.toString();
    }
}
