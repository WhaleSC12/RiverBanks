import org.json.simple.JSONAware;

import java.util.ArrayList;

public class Test implements JSONAware {
    private String title;
    private String description;
    private ArrayList<Question> questionList;

    public Test(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "Course.Lesson.Test{" + "questions=" + questionList + '}';
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