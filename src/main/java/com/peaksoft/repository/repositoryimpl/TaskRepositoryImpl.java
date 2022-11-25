package com.peaksoft.repository.repositoryimpl;

import com.peaksoft.model.Lesson;
import com.peaksoft.model.Task;
import com.peaksoft.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Task> getAllTask() {
        return manager.createQuery("select t from Task t").getResultList();
    }

    @Override
    public List<Task> getAllTask(Long lessonId) {
        return manager.createQuery("select t from Task t where t.lesson.id = : lessonId",
                Task.class).setParameter("lessonId", lessonId).getResultList();
    }

    @Override
    public Task getTaskById(Long id) {
        return manager.find(Task.class, id);
    }

    @Override
    public void saveTask(Long lessonId, Task task) {
        Lesson lesson = manager.find(Lesson.class, lessonId);
        lesson.addTask(task);
        task.setLesson(lesson);
        manager.merge(task);
    }

    @Override
    public void updateTask(Long id, Task task) {
        Task task1 = manager.find(Task.class, id);
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadLine(task.getDeadLine());
        manager.merge(task1);
    }

    @Override
    public void deleteTask(Long id) {
        manager.remove(manager.find(Task.class, id));
    }
}
