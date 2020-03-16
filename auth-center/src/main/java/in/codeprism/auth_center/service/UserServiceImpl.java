package in.codeprism.auth_center.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codeprism.auth_center.model.User;
import in.codeprism.auth_center.repository.RoleRepository;
import in.codeprism.auth_center.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
	/*
	 * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	 */ 

	
	/*s*/	
    
    
   /*  @Override 
	 public User save(User user) {
    	user.setPassword(user.getPassword());
	 //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	 Set<Role> roles = new HashSet<Role>() ;
	 roles.addAll(roleRepository.findAll()); user.setRoles(roles);
	 return userRepository.save(user); }
    */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
