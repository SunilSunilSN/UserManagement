package com.roleManagment.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.roleManagment.Entity.Role;
import com.roleManagment.Entity.User;
import com.roleManagment.Repository.RoleRepository;
import com.roleManagment.Repository.UserRepository;
import com.roleManagment.Service.UserDataService;

@Controller
public class UserController {

	@Autowired
	UserRepository repo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	private UserDataService userDataService;

	@GetMapping("/test/sunil")
	@PreAuthorize("hasRole('ADMIN')")
	public String Test() {
		return "Hello Test";
	}

	@GetMapping("/user/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER') and #id == @userDataService.getCurrentUserId()")
	public String getUserData(@PathVariable("id") Long id, Model model) {
		Optional<User> user = userDataService.getByUserId(id);
		model.addAttribute("userDetails", user);
		return "Profile";
	}

	@GetMapping("/new")
	@PreAuthorize("hasAnyRole('CREATOR')")
	public String showNewProductForm() {
		return "new_product";
	}

	@GetMapping("/Registration")
	public String registrationForm(Model model) {
		model.addAttribute("user", new User());
		return "Registration";
	};

	@PostMapping("/save")
	public String createUser(@ModelAttribute User user) {
		userDataService.saveUser(user);
		return "Registration";
	};

	@GetMapping("/AllUsers")
	public String getAllUsers(Model model) {
		List<User> users = repo.findAll();
		List<Role> roles = roleRepo.findAll();
		model.addAttribute("usersList", users);
		model.addAttribute("roleList", roles);
		return "AllUsers";
	}

	@PostMapping("/UpdateRole/{userId}")
	public User updateUserRoles(@PathVariable("userId") Long userId, @RequestBody Set<Long> roleIds) {
		return userDataService.updateUserRole(userId, roleIds);
	}

	@GetMapping("/edit")
	public String showEditProductForm() {

		return "Edit";
	}

	@GetMapping("/delete")
	public String deleteProduct() {
		return "redirect";
	}

}
