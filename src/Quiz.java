package src;

import org.json.simple.JSONAware;

import java.util.ArrayList;

/**
 * src.Test class used by lessons for teachers to create a series of questions through which to judge a student's level of knowledge
 */
public class Quiz implements JSONAware {
    private String title;
    private String description;
    private ArrayList<Question> questionList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Quiz(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
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
        return "src.Test{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", questionList=" + questionList +
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
        sb.append("\"questions\":");
        sb.append('[');
        boolean first = true;
        for (Question q : questionList) {
            if (!first) sb.append(',');
            else first = false;
            sb.append(q.toJSONString());
        }
        sb.append(']');
        sb.append('}');
        return sb.toString();
    }


}