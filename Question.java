import org.json.simple.JSONAware;

import java.util.AbstractMap;
import java.util.ArrayList;

public class Question implements JSONAware {
    private String prompt;
    // string is the text displayed to the user, bool is whether the user gets point for choosing that answer
    private ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList;

    public ArrayList<AbstractMap.SimpleEntry<String, Boolean>> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList) {
        this.answerList = answerList;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');

        sb.append("\"prompt\":");
        sb.append('"');
        sb.append(prompt);
        sb.append('"');
        sb.append(',');
        sb.append("\"answers\":");
        sb.append('[');
        boolean first = true;
        for (var v : answerList) {
            if (!first) sb.append(',');
            else first = false;
            sb.append('{');
            sb.append("\"text\":");
            sb.append('"');
            sb.append(v.getKey());
            sb.append('"');
            sb.append(',');
            sb.append("\"correct\":");
            sb.append(v.getValue());
            sb.append('}');
        }
        sb.append(']');

        sb.append('}');

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Course.Lesson.Test.Question{" + "prompt='" + prompt + '\'' + ", answerList=" + answerList + '}';
    }
}