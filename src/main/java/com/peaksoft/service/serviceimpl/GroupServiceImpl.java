package com.peaksoft.service.serviceimpl;

import com.peaksoft.model.Group;
import com.peaksoft.repository.GroupRepository;
import com.peaksoft.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    @Override
    public List<Group> getAllGroup() {
        return groupRepository.getAllGroup();
    }

    @Override
    public List<Group> getAllGroup(Long courseId) {
        return groupRepository.getAllGroup(courseId);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.getGroupById(id);
    }

    @Override
    public void saveGroup(Long courseId, Group group) {
        groupRepository.saveGroup(courseId, group);
    }

    @Override
    public void updateGroup(Long id, Group group) {
        groupRepository.updateGroup(id, group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteGroup(id);
    }

    @Override
    public void assignGroup(Long courseId, Long id) throws IOException {
        groupRepository.assignGroup(courseId, id);
    }

}
