package src;

import org.json.simple.JSONAware;

/**
 * Modules are sub-topics within lessons which contain the actual informative content
 */
public class Module implements JSONAware, TextFileAware {
    private String title;
    private String description;
    private String content;


    public Module(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "src.Module{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                '}';
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

    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        appendToStringBuilderJSONStyle("title", title, sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("description", description, sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("content", content, sb);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String toFileString() {
        return this.title +
                "\n" +
                this.description +
                "\n" +
                this.content;
    }

    @Override
    public String getFileName() {
        return this.title;
    }
}