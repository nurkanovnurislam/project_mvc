package com.peaksoft.repository.repositoryimpl;

import com.peaksoft.model.Company;
import com.peaksoft.model.Course;
import com.peaksoft.repository.CourseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Course> getAllCourse() {
        return manager.createQuery("select c from Course c").getResultList();
    }

    @Override
    public List<Course> getAllCourse(Long companyId) {
        return manager.createQuery("select c from Course c where c.company.id = : companyId",
                Course.class).setParameter("companyId", companyId).getResultList();
    }

    @Override
    public Course getCourseById(Long id) {
        return manager.find(Course.class, id);
    }

    @Override
    public void saveCourse(Long companyId, Course course) {
        Company company = manager.find(Company.class, companyId);
        company.addCourse(course);
        course.setCompany(company);
        manager.merge(course);
    }

    @Override
    public void updateCourse(Long id, Course course) {
        Course course1 = manager.find(Course.class, id);
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        manager.merge(course1);
    }

    @Override
    public void deleteCourse(Long id) {
        manager.remove(manager.find(Course.class, id));
    }
}
