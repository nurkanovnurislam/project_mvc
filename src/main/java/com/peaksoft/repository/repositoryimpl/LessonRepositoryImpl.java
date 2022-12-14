package com.peaksoft.repository.repositoryimpl;


import com.peaksoft.model.Course;
import com.peaksoft.model.Lesson;
import com.peaksoft.repository.LessonRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Lesson> getAllLesson() {
        return manager.createQuery("select l from Lesson l").getResultList();
    }

    @Override
    public List<Lesson> getAllLesson(Long courseId) {
        return manager.createQuery("select l from Lesson l where l.course.id = : courseId",
                Lesson.class).setParameter("courseId", courseId).getResultList();
    }

    @Override
    public Lesson getLessonById(Long id) {
        return manager.find(Lesson.class, id);
    }

    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        Course course = manager.find(Course.class, courseId);
        course.addLesson(lesson);
        lesson.setCourse(course);
        manager.merge(lesson);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        Lesson lesson1 = manager.find(Lesson.class, id);
        lesson1.setLessonName(lesson.getLessonName());
        manager.merge(lesson1);
    }

    @Override
    public void deleteLesson(Long id) {
        manager.remove(manager.find(Lesson.class, id));
    }
}
