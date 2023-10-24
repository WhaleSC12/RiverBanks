package src;

import org.json.simple.JSONAware;

import java.util.AbstractMap;
import java.util.ArrayList;

/**
 * Questions held within a src.Test
 */
public class Question implements JSONAware {
    private String prompt;
    // string is the text displayed to the user, bool is whether the user gets point for choosing that answer
    private ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList = new ArrayList<>();

    public ArrayList<AbstractMap.SimpleEntry<String, Boolean>> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "src.Question{" +
                "prompt='" + prompt + '\'' +
                ", answerList=" + answerList +
                '}';
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
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

        appendToStringBuilderJSONStyle("prompt", prompt, sb);
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

}