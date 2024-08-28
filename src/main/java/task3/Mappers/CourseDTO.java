package task3.Mappers;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CourseDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "description is mandatory")
    private String description;

    @Min(value = 1, message = "Credits must be greater than 0")
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
