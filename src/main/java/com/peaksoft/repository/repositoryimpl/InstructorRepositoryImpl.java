package com.peaksoft.repository.repositoryimpl;

import com.peaksoft.model.Course;
import com.peaksoft.model.Instructor;
import com.peaksoft.repository.InstructorRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Instructor> getAllInstructor() {
        return manager.createQuery("select i from Instructor i").getResultList();
    }

    @Override
    public List<Instructor> getAllInstructor(Long courseId) {
        return manager.createQuery("select i from Instructor i where i.course.id = : courseId",
                Instructor.class).setParameter("courseId", courseId).getResultList();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return manager.find(Instructor.class, id);
    }

    @Override
    public void saveInstructor(Long courseId, Instructor instructor) {
        System.out.println("5");
        Course course = manager.find(Course.class, courseId);
        course.addInstructor(instructor);
        instructor.setCourse(course);
        manager.merge(instructor);
        System.out.println("6");
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = manager.find(Instructor.class, id);
        instructor1.setFirstname(instructor.getFirstname());
        instructor1.setLastname(instructor.getLastname());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        manager.merge(instructor1);
    }

    @Override
    public void deleteInstructor(Long id) {
        manager.remove(manager.find(Instructor.class, id));
    }
}
