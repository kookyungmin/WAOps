package com.kookyungmin.waops.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kookyungmin.waops.domain.Criteria;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.debug("home>>>>>>");
		model.addAttribute("myCondition", "home");
		return "home";
	}
	@RequestMapping(value = "/questions/register", method = RequestMethod.GET)
	public String registerQuestion(Model model) {
		logger.debug("registerQuestion>>>>>>>>");
		model.addAttribute("myCondition", "question");
		return "/questions/register";
	}
	
	@RequestMapping(value = "/questions/update", method = RequestMethod.GET)
	public String updateQuestion(@RequestParam("qno") int qno, 
								 @ModelAttribute("cri") Criteria cri,
								 Model model) {
		logger.debug("registerQuestion>>>>>>>>qno={}", qno);
		model.addAttribute("myCondition", "question");
		model.addAttribute("qno", qno);
		return "/questions/update";
	}
	
	@RequestMapping(value = "/questions/read", method = RequestMethod.GET)
	public String readQuestion(@RequestParam("qno") int qno,
			@ModelAttribute("cri") Criteria cri,
			Model model) {
		logger.debug("readQuestions>>>>>>qno={}", qno);
		model.addAttribute("myCondition", "question");
		model.addAttribute("qno", qno);
		return "/questions/read";
	}
	
	@RequestMapping(value = "/questions/all", method = RequestMethod.POST)
	public String questionListPage(Model model, @ModelAttribute("cri") Criteria cri) {
		logger.debug("questionsListPage>>>>>>");
		model.addAttribute("myCondition", "question");
		return "/questions/listPage";
	}
	
	
}
