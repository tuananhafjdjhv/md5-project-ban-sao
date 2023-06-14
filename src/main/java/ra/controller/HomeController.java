package ra.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
@CrossOrigin("*")
public class HomeController {
    @RequestMapping({"","/example"})
    public String example(Model model) {
        System.out.println("vào view không");
        // Đưa dữ liệu cần thiết vào model
        model.addAttribute("message", "Hello, World!");

        // Trả về tên của file template để render
        return "home";
    }
}
