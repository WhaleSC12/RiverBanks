package src;

import org.json.simple.JSONAware;

import java.util.ArrayList;

/**
 * Lessons are groups of modules centered around some topic
 */
public class Lesson implements JSONAware {

    private final ArrayList<Comment> commentList;
    private final ArrayList<Module> moduleList;

    public Quiz getTest() {
        return quiz;
    }

    private Quiz quiz;
    private String title;
    private String description;

    public Lesson(String title, String description, Quiz quiz) {
        this.title = title;
        this.description = description;
        this.commentList = new ArrayList<>();
        this.moduleList = new ArrayList<>();
        this.quiz = quiz;
    }

    public ArrayList<Module> getModuleList() {
        return moduleList;
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
    public String toString() {
        return "src.Lesson{" +
                "commentList=" + commentList +
                ", moduleList=" + moduleList +
                ", test=" + quiz +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
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
            sb.append(c.toJSONString());
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
            sb.append(m.toJSONString());
        }

        sb.append(']');
        sb.append(',');
        sb.append("\"test\":");
        sb.append(quiz.toJSONString());

        sb.append('}');

        return sb.toString();
    }
}