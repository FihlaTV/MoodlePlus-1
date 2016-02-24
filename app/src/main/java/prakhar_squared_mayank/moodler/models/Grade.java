package prakhar_squared_mayank.moodler.models;

/**
 * Created by Prakhar on 2/24/2016.
 */
public class Grade {

    private String name;
    private int user_id;
    private int weitage;
    private int maxMarks;
    private int score;
    private int id;
    private int registeredCourseId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getWeitage() {
        return weitage;
    }

    public void setWeitage(int weitage) {
        this.weitage = weitage;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegisteredCourseId() {
        return registeredCourseId;
    }

    public void setRegisteredCourseId(int registeredCourseId) {
        this.registeredCourseId = registeredCourseId;
    }
}
