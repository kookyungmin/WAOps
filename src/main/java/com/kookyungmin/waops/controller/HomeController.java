package com.kookyungmin.waops.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("isEdit", false);
		return "/questions/register";
	}
	
	@RequestMapping(value = "/questions/update", method = RequestMethod.GET)
	public String updateQuestion(@RequestParam int qno, Model model) {
		logger.debug("registerQuestion>>>>>>>>qno={}", qno);
		model.addAttribute("myCondition", "question");
		model.addAttribute("isEdit", true);
		model.addAttribute("qno", qno);
		return "/questions/update";
	}
	
	@RequestMapping(value = "/questions/all", method = RequestMethod.GET)
	public String questionListPage(Model model) {
		logger.debug("questionsListPage>>>>>>");
		model.addAttribute("myCondition", "question");
		return "/questions/listPage";
	}
	
	@RequestMapping(value = "/questions/read", method = RequestMethod.GET)
	public String questionListPage(Model model, @RequestParam int qno) {
		logger.debug("questionsListPage>>>>>>qno={}", qno);
		model.addAttribute("myCondition", "question");
		model.addAttribute("qno", qno);
		return "/questions/read";
	}
	
}
