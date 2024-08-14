package Controller;

public class Course {
    int id;
    String name;
    String description;
    int credit;

    public Course(String name){
        this.name = name;
    }

    public Course(int id, String name, String description, int credit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.credit = credit;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

