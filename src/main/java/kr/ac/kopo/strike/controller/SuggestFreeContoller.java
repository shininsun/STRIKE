package kr.ac.kopo.strike.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.kopo.strike.model.SuggestFree;
import kr.ac.kopo.strike.service.SuggestFreeService;

@Controller
@RequestMapping("/suggestfree")
public class SuggestFreeContoller {
	final String path = "suggestfree/";
	
	private static final Logger logger = LoggerFactory.getLogger(SuggestFreeContoller.class);
	
	@Autowired
	SuggestFreeService service;
	
	@GetMapping("list")
	public String list(Model model) {
		List<SuggestFree> list = service.list();
				
		model.addAttribute("list", list);
		
		return path + "list";
	}
	
	@GetMapping("/add")
	public String add() {
		return path + "add";
	}
	
	@PostMapping("/add")
	public String add(SuggestFree item) {
		service.add(item);
		
		return "redirect:list";
	}
	
	@GetMapping("/delete/{freeCode}")
	public String delete(@PathVariable int freeCode) {
		service.delete(freeCode);
		
		return "redirect:../list";
	}
	
	@GetMapping("/update/{freeCode}" )
	public String update(@PathVariable int freeCode, Model model) {
		SuggestFree item = service.item(freeCode);
		
		model.addAttribute("item", item);
		
		return path + "update";
	}
	
	@PostMapping("/update/{freeCode}")
	public String update(@PathVariable int freeCode, SuggestFree item) {
		service.update(item);
		System.out.println(item);
		return "redirect:../list";
	}
	
	@GetMapping("/view/{freeCode}")
	public String view(@PathVariable int freeCode,Model model) {
		SuggestFree item = service.item(freeCode);
		model.addAttribute("item", item);
		
		return path+"view";
	}
	
	@RequestMapping(value= "/readView", method = RequestMethod.GET)
	public String read(SuggestFree suggestFree,Model model) throws Exception{
		
		logger.info("read");
		model.addAttribute("read", service.read(suggestFree.getFreeCode()));
		
		return "suggest/readView";
	}
		
}
