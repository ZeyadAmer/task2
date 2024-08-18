package Controller;

import javax.persistence.*;


@Entity
@Table(name="assessment")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Getters and setters
    public Assessment() {}
    public Assessment(String content, Course course) {
        this.content = content;
        this.course = course;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }


}