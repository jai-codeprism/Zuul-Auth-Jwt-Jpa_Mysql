package in.codeprism.auth_center.service;

import in.codeprism.auth_center.model.User;

public interface UserService {
	
	
	//void save(User user);
	 
	User findByUsername(String username);
	 }