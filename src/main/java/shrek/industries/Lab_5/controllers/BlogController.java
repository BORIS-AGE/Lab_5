package shrek.industries.Lab_5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shrek.industries.Lab_5.repository.PostsRepository;
import shrek.industries.Lab_5.repository.model.Post;

@Controller
public class BlogController {

    PostsRepository repository = PostsRepository.getInstance();

    @GetMapping("/blog")
    public String blogMain(Model model) {
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, Model model) {
        repository.savePost(new Post(System.currentTimeMillis() + "", title));
        return "home";
    }
}
