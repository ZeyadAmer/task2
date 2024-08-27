package task3.Mappers;

public class CourseDTO {
    private String name;
    private String description;
    private int credit;

    public CourseDTO() {}
    public CourseDTO(String name, String description, int credit) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getcredit() {
        return credit;
    }
    public void setcredit(int id) {
        this.credit = id;
    }
}
