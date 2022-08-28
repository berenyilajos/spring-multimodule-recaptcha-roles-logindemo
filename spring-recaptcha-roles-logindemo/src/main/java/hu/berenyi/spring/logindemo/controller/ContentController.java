package hu.berenyi.spring.logindemo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/content")
@Slf4j
public class ContentController {
	
	@RequestMapping(value = "/content", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String contentPage() {
		log.debug("contentPage called");
		return "content/content";
	}
	
}
