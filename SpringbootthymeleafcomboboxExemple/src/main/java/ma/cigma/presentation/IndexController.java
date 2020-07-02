package ma.cigma.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ma.cigma.domaine.Order;

@Controller
@RequestMapping({ "/", "/index" })
public class IndexController {

    @GetMapping
    public String main(Model model) {
        model.addAttribute("order", new Order());
        return "index";
    }

    @PostMapping
    public String save(Order order, Model model) {
        model.addAttribute("order", order);
        return "result";
    }
}
