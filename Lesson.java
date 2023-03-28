import org.json.simple.JSONAware;

import java.util.ArrayList;

public class Lesson implements JSONAware {

    private final ArrayList<Comment> commentList;
    private final ArrayList<Module> moduleList;
    private Test test;
    private String title;
    private String description;

    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

    public Lesson(String title, String description, Test test) {
        this.title = title;
        this.description = description;
        this.commentList = new ArrayList<>();
        this.moduleList = new ArrayList<>();
        this.test = test;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
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

    private void appendToStringBuilderJSONStyle(String key, String value, StringBuilder sb) {
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

        appendToStringBuilderJSONStyle("title", title, sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("description", description, sb);
        sb.append(',');
        sb.append('"');
        sb.append("comments");
        sb.append('"');
        sb.append(":");
        sb.append('[');

        boolean first = true;
        for (Comment c : commentList) {
            if (first) first = false;
            else sb.append(',');
            c.toJSONString();
        }

        sb.append(']');
        sb.append(',');
        sb.append('"');
        sb.append("modules");
        sb.append('"');
        sb.append(":");
        sb.append('[');

        first = true;
        for (Module m : moduleList) {
            if (first) first = false;
            else sb.append(',');
            m.toJSONString();
        }

        sb.append(']');
        sb.append(',');
        sb.append("\"test\":");
        sb.append(test.toJSONString());

        sb.append('}');

        return sb.toString();
    }
}