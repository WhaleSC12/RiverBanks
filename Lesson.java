import org.json.simple.JSONAware;

import java.util.ArrayList;

public class Lesson implements JSONAware {

    private final ArrayList<Comment> commentList;
    private final ArrayList<Module> moduleList;
    private String title;
    private String description;
    private String content;

    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

    public Lesson(String title, String description, String content) {
        this.title = title;
        this.content = content;
        this.description = description;
        this.commentList = new ArrayList<>();
        this.moduleList = new ArrayList<>();

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        appendToStringBuilderJSONStyle("content", content, sb);
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

        sb.append('}');

        return sb.toString();
    }
}