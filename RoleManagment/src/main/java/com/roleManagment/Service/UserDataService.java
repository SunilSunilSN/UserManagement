package com.roleManagment.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.roleManagment.Entity.MyUserDetails;
import com.roleManagment.Entity.Role;
import com.roleManagment.Entity.User;
import com.roleManagment.Repository.RoleRepository;
import com.roleManagment.Repository.UserRepository;

@Service
public class UserDataService {

	@Autowired
	private UserRepository repo;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepo;

	private static final String TOPIC = "user-events";

	public Optional<User> getByUserId(Long Id) {
		Optional<User> user = repo.findById(Id);
		// kafkaTemplate.send(TOPIC, "User registered: " + user.get().getFullName());
		return repo.findById(Id);
	}

	public Long getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof MyUserDetails) {
			return ((MyUserDetails) authentication.getPrincipal()).getId();
		}
		return null;
	}

	public String saveUser(User user) {
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role deafultRole = roleRepo.findByName("USER");
		user.getRoles().add(deafultRole);
		repo.save(user);
		return "Saved";
	}

	public User updateUserRole(Long userId, Set<Long> roleIds) {
		User user = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id " + userId));
		Set<Role> roles = roleRepo.findAllById(roleIds).stream().collect(Collectors.toSet());
		user.setRoles(roles);
		return repo.save(user);
	}
}
