package prakhar_squared_mayank.moodler.models;

import com.android.volley.toolbox.StringRequest;

import java.io.Serializable;

/**
 * Created by Prakhar on 2/24/2016.
 */
public class Assignment implements Serializable{
    public String name;
    public String startDate;
    public String endDate;
    public String description;
    public int id;
    public String courseCode;


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;

    }

    public String getStartDate() {
        return startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;

    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
