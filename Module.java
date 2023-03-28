import org.json.simple.JSONAware;

public class Module implements JSONAware {
    private Test test;
    private String title;
    private String description;
    private String content;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Module(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public String toJSONString() {
        return null;
    }


}