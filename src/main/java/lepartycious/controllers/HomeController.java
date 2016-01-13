package lepartycious.controllers;

import lepartycious.dtos.requestDTOs.HomeRequestDTO;
import lepartycious.dtos.responseDTOs.HomeResponseDTO;
import lepartycious.models.User;
import lepartycious.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/rest/v1/user")
public class HomeController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(method=RequestMethod.POST)
	public String saveUser(@RequestBody HomeRequestDTO homeDTO) {
		System.out.println("User Saved");
		return "save";
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		System.out.println("User Deleted");
		return "delete";
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public String updateUser(@RequestBody HomeRequestDTO homeDTO) {
		System.out.println("User Updated");
		return "update";
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public HomeResponseDTO getUserById(@PathVariable Long id) {
		System.out.println("User Get By Id");
		HomeResponseDTO homeResponseDTO = new HomeResponseDTO();
		homeResponseDTO.setName("Mohit");
		return homeResponseDTO;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getAllUsers() {
		System.out.println("User Get All");
		return "getAllUsers";
	}

}
