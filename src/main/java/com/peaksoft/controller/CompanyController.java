package com.peaksoft.controller;

import com.peaksoft.model.Company;
import com.peaksoft.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/getAllCompany")
    public String getAllCompany(Model model) {
        model.addAttribute("getAllCompany", companyService.getAllCompany());
        return "/company/get_all_company";
    }

    @GetMapping("/getCompanyById/{id}")
    public String getCompanyById(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "redirect:/getAllCompany";
    }

    @GetMapping("/new")
    public String newCompany(Model model) {
        model.addAttribute("newCompany", new Company());
        return "/company/save_company";
    }

    @PostMapping("/save") // th:action="@{save}"
    public String saveCompany(@ModelAttribute("newCompany") Company company) { // th:object="${newCompany}"
        companyService.saveCompany(company);
        return "redirect:/getAllCompany";
    }

    @GetMapping("/updateCompany")
    public String updateCompany(@RequestParam("companyId") Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "/company/update_company";
    }

    @PostMapping("saveUpdateCompany")
    public String saveUpdateCompany(@ModelAttribute("company") Company company) {
        companyService.updateCompany(company);
        return "redirect:/getAllCompany";
    }

    @GetMapping("/deleteCompany")
    public String deleteCompany(@RequestParam("companyId") Long id) {
        companyService.deleteCompany(companyService.getCompanyById(id));
        return "redirect:/getAllCompany";
    }

}
