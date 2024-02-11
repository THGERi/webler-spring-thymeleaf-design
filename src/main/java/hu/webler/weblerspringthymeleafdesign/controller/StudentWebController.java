package hu.webler.weblerspringthymeleafdesign.controller;

import hu.webler.weblerspringthymeleafdesign.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/thy")
public class StudentWebController { // not the best naming, but in our case it is ok ...


    private final StudentService studentService;

    @GetMapping
    public String homePage() {
        return "index";
    }

    @GetMapping("/students")
    public String renderAllStudentsOnWeb(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }

    @GetMapping("/students-hun")
    public String renderAllStudentsHunOrderOnWeb(Model model) {
        model.addAttribute("students", studentService.getStudentsHungarian());
        return "students";
    }

    @GetMapping("/students/search")
    public String renderAllStudentsOnWeb(@RequestParam String search, Model model) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("students", studentService.searchStudentsByEmail(search));
        }
        return "students";
    }
}
