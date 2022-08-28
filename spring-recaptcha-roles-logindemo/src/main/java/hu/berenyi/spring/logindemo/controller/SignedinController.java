package hu.berenyi.spring.logindemo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/signedin")
@Slf4j
public class SignedinController {
	
	@RequestMapping(value = "/signedin", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String signedinPage() {
		log.debug("signedinPage called");
		return "signedin/signedin";
	}

}
