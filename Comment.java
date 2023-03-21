import java.util.ArrayList;

public class Comment {
    private User author;
    private String content;
    private ArrayList<Comment> replyList;

    public Comment(User author, String content) {
        this.author = author;
        this.content = content;
    }

    public ArrayList<Comment> fetchReplies() {
        return replyList;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
