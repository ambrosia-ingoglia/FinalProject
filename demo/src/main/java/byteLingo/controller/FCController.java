package byteLingo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import byteLingo.entity.FCEntity;
import byteLingo.service.FCService;
import byteLingo.service.UserService;

@Controller
public class FCController {

	@Autowired
    private FCService FCService;
	
	
	@GetMapping("/flashcards")
	String getFlashCards() {
		return "flashcards";
	}
	
	@GetMapping("/personal_fc")
	String getMyFC(@RequestParam("username") String user, Model model) {
		model.addAttribute("username", user);
		
		return "personal_fc";
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
	
	@GetMapping("/another_page")
    public String getmyfc(@RequestParam("username") String user, @RequestParam("item") String table, RedirectAttributes model, Model model2) {
        List<FCEntity> rows = FCService.getMyFlaschcards(table);
        
        //System.out.print(rows);
        
        model.addAttribute("username", user);
        model.addFlashAttribute("rows", rows);
        return "redirect:/personal_fc"; // Return the name of the view
    }
	
	
	@GetMapping("/newfc")
	String getnewFC(@RequestParam("username") String user, Model model) {
		model.addAttribute("username", user);
		
		return "newfc";
	}
    
	
	
	@Autowired
    private UserService UserService;
	
    @PostMapping("/newTable")
    public String createFlashCard(@RequestParam("username") String user, @RequestParam("table_name") String tablename, @RequestParam("all_data") String datas, Model model) {
    	//Checks if the flashcard already existed in the SQL
    	Integer result = FCService.check(tablename);
    	
    	model.addAttribute("username", user);
    	
    	if(result > 0) {
    		return "result3";
    	}
    	
    	//add the new table in user_info for personal Flash cards
    	UserService.addFlashCard(user, tablename);
    	
    	FCService.newFC(tablename, datas);
    	
    	return "learn";
    }
}
