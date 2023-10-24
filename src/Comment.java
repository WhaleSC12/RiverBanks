package src;

import org.json.simple.JSONAware;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Holds text, author, and replies for some user to comment upon a lesson
 */
public class Comment implements JSONAware {
    private final UUID author;
    private final String content;
    private ArrayList<Comment> commentList;

    public Comment(UUID author, String content) {
        this.author = author;
        this.content = content;
        this.commentList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "src.Comment{" +
                "author=" + author +
                ", content='" + content + '\'' +
                ", commentList=" + commentList +
                '}';
    }

    public UUID getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
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
        appendToStringBuilderJSONStyle("userUUID", getAuthor().toString(), sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("content", getContent(), sb);

        sb.append(',');
        sb.append('"');
        sb.append("comments");
        sb.append('"');
        sb.append(":");
        sb.append('[');

        boolean first = true;
        for (Comment c : commentList) {
            if (first) first = false;
            else sb.append(',');
            c.toJSONString();
        }

        sb.append(']');

        sb.append('}');
        return sb.toString();
    }
}
