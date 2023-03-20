import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Lesson {

    public Lesson(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    private String title;
    private String description;
    private String topic;
    private String content;
    private Test test;
    private double grade;

    public JSONObject getLessonData() {
        JSONObject jobj = new JSONObject();
        jobj.put("title", title);
        jobj.put("content", content);
        JSONArray test = new JSONArray();
        for (Question q :
                getTest().getQuestions()) {
            test.add(q);
        }
        jobj.put("test", test);

        return jobj;
    }
}
