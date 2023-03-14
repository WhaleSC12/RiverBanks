import javax.xml.stream.events.Comment;

public class Lesson {
    private String title;
    private String description;
    private String topic;
    private String content;
    private Test test;
    private Comment response;

    public Lesson(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Comment getResponse() {
        return response;
    }
}
