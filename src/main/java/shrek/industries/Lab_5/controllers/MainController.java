package shrek.industries.Lab_5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shrek.industries.Lab_5.repository.PostsRepository;
import shrek.industries.Lab_5.repository.model.Post;

import java.util.List;

@Controller
public class MainController {
    private String savedSortValue = "";
    PostsRepository repository = PostsRepository.getInstance();

    @GetMapping("/")
    public String home(Model model) {
        List<Post> list = repository.getPosts();
        StringBuilder mainStringValues = new StringBuilder();
        for (Post item : list) {
            if (savedSortValue.isEmpty() || item.value.contains(savedSortValue)) {
                mainStringValues.append(item.value + "\n");
            }
        }

        model.addAttribute("title", "Main page");
        model.addAttribute("values", mainStringValues.toString());
        return "home";
    }

    @PostMapping("/sort")
    public String postSearch(@RequestParam String sort, Model model) {
        savedSortValue = sort;

        return "home";
    }
}
