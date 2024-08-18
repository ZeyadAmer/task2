package Controller;



public class Course {
    private int id;
    private String name;
    private String description;
    private int credit;



    // Constructor with all fields
    public Course(int id, String name, String description, int credit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.credit = credit;
    }

    // Constructor with name only
    public Course(String name) {
        this.name = name;
    }


    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for credit
    public int getCredit() {
        return credit;
    }

    // Setter for credit
    public void setCredit(int credit) {
        this.credit = credit;
    }
}
