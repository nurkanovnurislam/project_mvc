package com.peaksoft.repository.repositoryimpl;

import com.peaksoft.model.Group;
import com.peaksoft.model.Student;
import com.peaksoft.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Student> getAllStudent() {
        return manager.createQuery("select s from Student s").getResultList();
    }

    @Override
    public List<Student> getAllStudent(Long groupId) {
        return manager.createQuery("select s from Student s where s.group.id = : groupId",
                Student.class).setParameter("groupId", groupId).getResultList();
    }

    @Override
    public Student getStudentById(Long id) {
        return manager.find(Student.class, id);
    }

    @Override
    public void saveStudent(Long groupId, Student student) {
        Group group = manager.find(Group.class, groupId);
        group.addStudent(student);
        student.setGroup(group);
        manager.merge(student);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = manager.find(Student.class, id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        manager.merge(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        manager.remove(manager.find(Student.class,id));
    }
}
