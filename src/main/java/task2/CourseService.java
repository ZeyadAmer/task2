package task2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {
    private CourseRecommender courseRecommender;


    @Autowired
    public CourseService(@Qualifier("basicCourseRecommender")CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }

//    @Autowired
//    public void setRecommendedCourses(@Qualifier("advancedCourseRecommender")CourseRecommender courseRecommender){
//        this.courseRecommender = courseRecommender;
//    }


    public List<Course> getRecommendedCourses() {

        return courseRecommender.recommendedCourses();
    }




}
