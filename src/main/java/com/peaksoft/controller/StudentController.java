package com.peaksoft.controller;

import com.peaksoft.model.Student;
import com.peaksoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAllStudent/{groupId}")
    public String getAllStudent(@PathVariable Long groupId, Model model) {
        model.addAttribute("getAllStudent", studentService.getAllStudent());
        model.addAttribute("groupId", groupId);
        return "/student/see_all_student";
    }

    @GetMapping("/getAllStudentByGroupId/{groupId}")
    public String getAllStudentByCourseId(@PathVariable Long groupId, Model model) {
        model.addAttribute("getAllStudentByGroupId", studentService.getAllStudent(groupId));
        model.addAttribute("groupId", groupId);
        return "/student/get_all_student_by_group_id";
    }

    @GetMapping("/getStudentById/{id}")
    public String getStudentById(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "redirect:/getAllStudentByGroupId";
    }

    @GetMapping("/getAllStudentByGroupId/{groupId}/new")
    public String newStudent(@PathVariable Long groupId, Model model) {
        model.addAttribute("newStudent", new Student());
        model.addAttribute("groupId", groupId);
        return "/student/save_student";
    }

    @PostMapping("/{groupId}/saveStudent")
    public String saveStudent(@PathVariable Long groupId, @ModelAttribute("newStudent") Student student) {
        studentService.saveStudent(groupId, student);
        return "redirect:/getAllStudentByGroupId/" + groupId;
    }

    @GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("updateStudent", student);
        model.addAttribute("groupId", student.getGroup().getId());
        return "/student/update_student";
    }

    @PostMapping("/{groupId}/{id}/saveUpdateStudent")
    public String saveUpdateStudent(@PathVariable Long groupId,
                                    @PathVariable Long id,
                                    @ModelAttribute("updateStudent") Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/getAllStudentByGroupId/" + groupId;
    }

    @GetMapping("/{groupId}/{id}/deleteStudentById")
    public String deleteStudentById(@PathVariable Long groupId, @PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/getAllLessonByCourseId/" + groupId;
    }



}