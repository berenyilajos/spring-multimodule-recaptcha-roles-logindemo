package hu.berenyi.spring.logindemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hu.berenyi.spring.logindemo.dto.UserDTO;
import hu.berenyi.spring.logindemo.helper.DTOHelper;
import hu.berenyi.spring.logindemo.model.User;
import hu.berenyi.spring.logindemo.services.UserServices;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
	
	@Autowired
	private UserServices userServices;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String allUsers(Model model) {
		log.debug("allUsers called");
		List<User> users = userServices.findAllUsers();
		List<UserDTO> listUsers = DTOHelper.toDTO(users);
		model.addAttribute("listUsers", listUsers);
		
		return "admin/users";
	}

}
