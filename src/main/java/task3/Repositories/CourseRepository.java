package task3.Repositories;

import Controller.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByName(String name);
    Optional<Course> findFirstByOrderByCreditAsc();


}
