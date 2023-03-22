import org.json.simple.JSONAware;

import java.util.ArrayList;
import java.util.UUID;

public class UserCourseData implements JSONAware {

    UUID userUUID;
    UUID courseUUID;
    int lessonsCompleted;
    ArrayList<Double> lessonGrades;

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

    public UserCourseData(UUID userUUID, UUID courseUUID, int lessonsCompleted, ArrayList<Double> lessonGrades) {
        this.userUUID = userUUID;
        this.courseUUID = courseUUID;
        this.lessonsCompleted = lessonsCompleted;
        this.lessonGrades = lessonGrades;
    }

    @Override
    public String toJSONString() {
        return null;
    }
}
