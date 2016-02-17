package prakhar_squared_mayank.moodler.models;

import java.io.Serializable;

/**
 * Created by Prakhar on 2/18/2016.
 */
public class Course implements Serializable {

        private String code;
        private String name;
        private String description;
        private int credits;
        private int id;
        private String ltp;
        public static final long serialVersionUID=1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLtp() {
        return ltp;
    }

    public void setLtp(String ltp) {
        this.ltp = ltp;
    }
}

