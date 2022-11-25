package com.peaksoft.repository.repositoryimpl;

import com.peaksoft.model.Course;
import com.peaksoft.model.Group;
import com.peaksoft.repository.GroupRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class GroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Group> getAllGroup() {
        return manager.createQuery("select g from Group g ").getResultList();
    }

    @Override
    public List<Group> getAllGroup(Long courseId) {
        List<Group> groups = manager.find(Course.class, courseId).getGroups();
        groups.forEach(System.out::println);
        return groups;
    }

    @Override
    public Group getGroupById(Long id) {
        return manager.find(Group.class, id);
    }

    @Override
    public void saveGroup(Long courseId, Group group) {
        Course course = manager.find(Course.class, courseId);
        course.addGroup(group);
        group.addCourse(course);
        manager.merge(group);
    }

    @Override
    public void updateGroup(Long id, Group group) {
        Group group1 = manager.find(Group.class, id);
        group1.setGroupName(group.getGroupName());
        group1.setDataOfStart(group.getDataOfStart());
        group1.setImage(group.getImage());
        manager.merge(group1);
    }

    @Override
    public void deleteGroup(Long id) {
        manager.remove(manager.find(Group.class, id));
    }

    @Override
    public void assignGroup(Long courseId, Long id) throws IOException {
        Course course = manager.find(Course.class, courseId);
        Group group = manager.find(Group.class, id);
        course.addGroup(group);
        group.addCourse(course);
        manager.merge(group);
        manager.merge(course);
    }
}