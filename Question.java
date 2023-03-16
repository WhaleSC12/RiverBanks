import java.util.AbstractMap;
import java.util.ArrayList;


public class Question {
    private String prompt;
    // string is the text displayed to the user, bool is whether the user gets point for choosing that answer
    private ArrayList<AbstractMap.SimpleEntry<String,Boolean>> answers;

    public void setPrompt (String prompt) {
          this.prompt = prompt;
    }
    public String getProompt() { 
          return prompt;
    }
   

 }
