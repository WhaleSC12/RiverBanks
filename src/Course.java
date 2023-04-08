package src;

import org.json.simple.JSONAware;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Data structure which holds course data such as the course's title, contents, and lessons
 */
public class Course implements JSONAware {
    private final UUID uuid;
    private String title;
    private String description;
    private UUID authorUUID;
    private ArrayList<Lesson> lessonList;
    private Language language;


    /**
     * src.Course constructor meant to be called when creating a new course, as said new course will lack a uuid.
     * This is equivalent to simply calling the normal constructor with UUID.randomUUID() for the first parameter.
     *
     * @param title src.Course Title
     * @param description course description
     * @param authorUUID author uuid
     * @param language coding src.Language the course teaches
     */
    public Course(String title, String description, UUID authorUUID, Language language) {
        this(UUID.randomUUID(), title, description, authorUUID, language);
    }

    /**
     * Common course constructor, used to create course objects from existing data
     *
     * @param uuid UUID of the course
     * @param title src.Course Title
     * @param description course description
     * @param authorUUID author uuid
     * @param language coding src.Language the course teaches
     */
    public Course(UUID uuid, String title, String description, UUID authorUUID, Language language) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.authorUUID = authorUUID;
        this.language = language;
        this.lessonList = new ArrayList<>();
    }

    public UUID getUUID() {
        return uuid;
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

    public UUID getAuthorUUID() {
        return authorUUID;
    }

    public void setAuthorUUID(UUID authorUUID) {
        this.authorUUID = authorUUID;
    }

    public ArrayList<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(ArrayList<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public void addLesson(Lesson lesson) {
        lessonList.add(lesson);
    }

    public void setLesson(int place, Lesson lesson) {
        lessonList.set(place, lesson);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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

        appendToStringBuilderJSONStyle("title", title, sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("description", description, sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("language", language.toString(), sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("authorUUID", authorUUID.toString(), sb);
        sb.append(',');
        sb.append('"');
        sb.append("lessons");
        sb.append('"');
        sb.append(":");
        sb.append('[');

        boolean first = true;
        for (Lesson l : lessonList) {
            if (!first) sb.append(',');
            else first = false;

            sb.append(l.toJSONString());

        }

        sb.append(']');

        sb.append('}');

        return sb.toString();
    }

    @Override
    public String toString() {
        return "src.Course{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorUUID=" + authorUUID +
                ", lessonList=" + lessonList +
                ", language=" + language +
                '}';
    }
}