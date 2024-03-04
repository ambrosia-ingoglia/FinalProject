package byteLingo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import byteLingo.entity.FCEntity;
import byteLingo.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FCController {

	@Autowired
    private UserService UserService;
	
	
	@GetMapping("/flashcards")
	String getFlashCards() {
		return "flashcards";
	}
	
	@GetMapping("/DSflashcard")
    public String getDS(RedirectAttributes model) {
        List<FCEntity> rows = UserService.getDSFlaschcards();
       
        model.addFlashAttribute("rows", rows);
        return "redirect:/flashcards"; // Return the name of the view
    }
}
