import org.json.simple.JSONAware;

import java.util.ArrayList;

public class Test implements JSONAware {
    private String testTitle;
    private String testDescription;
    private ArrayList<Question> questionList;

    public Test(String testTitle, String testDescription) {
        this.testTitle = testTitle;
        this.testDescription = testDescription;
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

    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean first = true;
        for (Question q : questionList) {
            if (!first) sb.append(',');
            else first = false;
            sb.append(q.toJSONString());
        }
        sb.append(']');

        return sb.toString();
    }


}