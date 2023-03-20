import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.util.AbstractMap;
import java.util.ArrayList;


public class Question implements JSONAware {
    private String prompt;

    public ArrayList<AbstractMap.SimpleEntry<String, Boolean>> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList) {
        this.answerList = answerList;
    }

    // string is the text displayed to the user, bool is whether the user gets point for choosing that answer
    private ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList;

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
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
        for (var v :
                answerList) {
            if (!first);
        }
        sb.append(']');
        sb.append('}');

        return sb.toString();
    }

    public JSONObject getJSONObject() {

        return null;
    }
}
