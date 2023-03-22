import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.UUID;

public class Course {

    private final UUID uuid;
    private String title;
    private String description;
    private UUID authorUUID;
    private ArrayList<Lesson> lessons;
    private Language language;

    public Course(String title, String description, UUID authorUUID, Language language) {
        this(UUID.randomUUID(), title, description, authorUUID, language);
    }

    public Course(UUID uuid, String title, String description, UUID authorUUID, Language language) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.authorUUID = authorUUID;
        this.language = language;
        this.lessons = new ArrayList<>();
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

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void setLesson(int place, Lesson lesson) {
        lessons.set(place, lesson);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorUUID=" + authorUUID +
                ", lessons=" + lessons +
                ", language=" + language +
                ", uuid=" + uuid +
                '}';
    }

    public JSONObject getUserCourseData() {
        JSONObject stuff = new JSONObject();
        stuff.put("courseTitle", title);
        stuff.put("description", description);
        stuff.put("authorUUID", authorUUID.toString()); // FIX
        stuff.put("language", language.toString());
        ArrayList<JSONObject> lessonArr = new ArrayList<>();
        for (Lesson l : getLessons()) {
            lessonArr.add(l.getLessonData());
        }
        stuff.put("lessons", lessonArr); // TODO properly implement jsonaware instead of this trash
        return stuff;
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

    public static class Lesson {

        private String title;
        private String description;
        private String content;
        private Test test;

        public Lesson(String title, String description, String content, Test test) {
            this.title = title;
            this.content = content;
            this.description = description;
            this.test = test;
        }

        @Override
        public String toString() {
            return "Course.Lesson{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", content='" + content + '\'' +
                    ", test=" + test +
                    '}';
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

        public Test getTest() {
            return test;
        }

        public void setTest(Test test) {
            this.test = test;
        }

        public JSONObject getLessonData() {
            JSONObject jobj = new JSONObject();
            jobj.put("title", title);
            jobj.put("content", content);
            JSONArray test = new JSONArray();
            for (Test.Question q :
                    getTest().getQuestions()) {
                test.add(q);
            }
            jobj.put("test", test);

            return jobj;
        }

        public static class Test {

            private ArrayList<Question> questions;

            public Test() {
            }

            public ArrayList<Question> getQuestions() {
                return questions;
            }

            public void setQuestions(ArrayList<Question> questions) {
                this.questions = questions;
            }

            @Override
            public String toString() {
                return "Course.Lesson.Test{" +
                        "questions=" + questions +
                        '}';
            }

            public static class Question implements JSONAware {
                private String prompt;
                // string is the text displayed to the user, bool is whether the user gets point for choosing that answer
                private ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList;

                public ArrayList<AbstractMap.SimpleEntry<String, Boolean>> getAnswerList() {
                    return answerList;
                }

                public void setAnswerList(ArrayList<AbstractMap.SimpleEntry<String, Boolean>> answerList) {
                    this.answerList = answerList;
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

                @Override
                public String toString() {
                    return "Course.Lesson.Test.Question{" +
                            "prompt='" + prompt + '\'' +
                            ", answerList=" + answerList +
                            '}';
                }
            }
        }
    }
}
