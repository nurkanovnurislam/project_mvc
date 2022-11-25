package com.peaksoft.controller;

import com.peaksoft.model.Group;
import com.peaksoft.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/getAllGroup/{courseId}")
    public String getAllGroup(@PathVariable Long courseId, Model model) {
        model.addAttribute("getAllGroup", groupService.getAllGroup());
        model.addAttribute("courseId", courseId);
        return "/group/see_all_group";
    }

    @GetMapping("/getAllGroupByCourseId/{courseId}")
    public String getAllGroupByCourseId(@PathVariable Long courseId, Model model) {
        model.addAttribute("getAllGroupByCourseId", groupService.getAllGroup(courseId));
        model.addAttribute("courseId", courseId);
        return "/group/get_all_group_by_course_id";
    }

    @GetMapping("/getGroupById/{id}")
    public String getGroupById(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.getGroupById(id));
        return "/course/get_all_course_by_company_id";
    }

    @GetMapping("/getAllGroupByCourseId/{courseId}/new")
    public String newGroup(@PathVariable Long courseId, Model model) {
        model.addAttribute("newGroup", new Group());
        model.addAttribute("courseId", courseId);
        return "/group/save_group";
    }

    @PostMapping("/{courseId}/saveGroup")
    public String saveGroup(@PathVariable Long courseId, @ModelAttribute("newGroup") Group group) {
        groupService.saveGroup(courseId, group);
        return "redirect:/getAllGroupByCourseId/" + courseId;
    }

    @GetMapping("/updateGroupByCourseId/{courseId}/{id}")
    public String updateGroupByCourseId(@PathVariable Long courseId,
                                        @PathVariable Long id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);
        model.addAttribute("courseId", courseId);
        return "/group/update_group";
    }

    @PostMapping("/{courseId}/{id}/saveUpdateGroupByCourseId")
    public String saveUpdateGroupByCourseId(@PathVariable Long courseId,
                                            @PathVariable Long id,
                                            @ModelAttribute("group") Group group) {
        groupService.updateGroup(id, group);
        return "redirect:/getAllGroupByCourseId/" + courseId;
    }

    @GetMapping("/{courseId}/{id}/deleteGroupById")
    public String deleteGroupById(@PathVariable Long courseId, @PathVariable Long id) {
        groupService.deleteGroup(id);
        return "redirect:/getAllGroupByCourseId/" + courseId;
    }


}
