package dk.levitate.test.levitate;

/**
 * Created by Nina on 14/01/2017.
 */

public class Boost {

    public String description;
    public String title;
    public int time;
    public boolean active;
    public Category category;
    public int id;

    public Boost(String description, String title, int time, Category category, boolean active){
        //TODO: Validate input
        this.active = active;
        this.title = title;
        this.time = time;
        this.category = category;
        this.description = description;
    }
    public Boost(){}

}
