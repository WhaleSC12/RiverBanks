import java.util.ArrayList;

public class Comment {
    private RegisteredUser author;
    private String content;
    private ArrayList<Comment> replyList;

    public Comment(RegisteredUser author, String content) {
        this.author = author;
        this.content = content;
    }
    public ArrayList<Comment> fetchReplies(){

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
