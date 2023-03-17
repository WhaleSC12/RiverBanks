import java.util.AbstractMap;
import java.util.ArrayList;


public class Question {
    private String prompt;

    public ArrayList<AbstractMap.SimpleEntry<String, Boolean>> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList) {
        this.answerList = answerList;
    }

    // string is the text displayed to the user, bool is whether the user gets point for choosing that answer
    private ArrayList<AbstractMap.SimpleEntry<String,Boolean>> answerList;

    public void setPrompt (String prompt) {
          this.prompt = prompt;
    }
    public String getPrompt() {
          return prompt;
    }
   

 }
