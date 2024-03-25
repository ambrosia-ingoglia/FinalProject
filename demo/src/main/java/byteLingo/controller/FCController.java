package byteLingo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import byteLingo.entity.FCEntity;
import byteLingo.service.FCService;

@Controller
public class FCController {

	@Autowired
    private FCService FCService;
	
	
	@GetMapping("/flashcards")
	String getFlashCards() {
		return "flashcards";
	}
	
	@GetMapping("/DSflashcard")
    public String getDS(RedirectAttributes model) {
        List<FCEntity> rows = FCService.getDSFlaschcards();
       
        model.addFlashAttribute("rows", rows);
        return "redirect:/ds"; // Return the name of the view
    }
	
	@GetMapping("/OOPflashcard")
    public String getOOP(RedirectAttributes model) {
        List<FCEntity> rows = FCService.getOOPFlaschcards();
       
        model.addFlashAttribute("rows", rows);
        return "redirect:/flashcards"; // Return the name of the view
    }
	
	@GetMapping("/SWEflashcard")
    public String getSWE(RedirectAttributes model) {
        List<FCEntity> rows = FCService.getOOPFlaschcards();
       
        model.addFlashAttribute("rows", rows);
        return "redirect:/flashcards"; // Return the name of the view
    }
}
