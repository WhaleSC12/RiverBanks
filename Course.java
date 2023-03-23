import org.json.simple.JSONAware;

import java.util.AbstractMap;
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

    public Course(String title, String description, UUID authorUUID, Language language) {
        this(UUID.randomUUID(), title, description, authorUUID, language);
    }

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

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorUUID=" + authorUUID +
                ", lessons=" + lessonList +
                ", language=" + language +
                ", uuid=" + uuid +
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

    public static class Lesson implements JSONAware {

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
            appendToStringBuilderJSONStyle("content", content, sb);
            sb.append(',');
            sb.append('"');
            sb.append("test");
            sb.append('"');
            sb.append(":");
            sb.append(test.toJSONString());

            sb.append('}');

            return sb.toString();
        }

        public static class Test implements JSONAware {

            private ArrayList<Question> questionList;

            public Test() {
            }

            public ArrayList<Question> getQuestionList() {
                return questionList;
            }

            public void setQuestionList(ArrayList<Question> questionList) {
                this.questionList = questionList;
            }

            @Override
            public String toString() {
                return "Course.Lesson.Test{" +
                        "questions=" + questionList +
                        '}';
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

                @Override
                public String toJSONString() {
                    StringBuilder sb = new StringBuilder();
                    sb.append('{');

                    sb.append("\"prompt\":");
                    sb.append('"');
                    sb.append(prompt);
                    sb.append('"');
                    sb.append(',');
                    sb.append("\"answers\":");
                    sb.append('[');
                    boolean first = true;
                    for (var v : answerList) {
                        if (!first) sb.append(',');
                        else first = false;
                        sb.append('{');
                        sb.append("\"text\":");
                        sb.append('"');
                        sb.append(v.getKey());
                        sb.append('"');
                        sb.append(',');
                        sb.append("\"correct\":");
                        sb.append(v.getValue());
                        sb.append('}');
                    }
                    sb.append(']');

                    sb.append('}');

                    return sb.toString();
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
