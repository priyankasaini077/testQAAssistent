package com.stackroute.user.service;

import com.stackroute.user.util.exception.UserAlreadyExistsException;
import com.stackroute.user.util.exception.UserNotFoundException;
import com.stackroute.user.model.User;
import com.stackroute.user.repository.UserAuthRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

   /*
	* Service classes are used here to implement additional business logic/validation 
	* This class has to be annotated with @Service annotation.
	* @Service - It is a specialization of the component annotation. It doesn't currently 
	* provide any additional behavior over the @Component annotation, but it's a good idea 
	* to use @Service over @Component in service-layer classes because it specifies intent 
	* better. Additionally, tool support and additional behavior might rely on it in the 
	* future.
	*/
@Service
public class UserAuthServiceImpl implements UserAuthService {

	/*
	 * Autowiring should be implemented for the UserAuthRepository and SQL Operation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
	UserAuthRepository userRepo;

	/*
	 * This method should be used to find an existing User with correct password.
	 */
    
    @Override
    public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userRepo.findByUserIdAndPassword(userId, password);
		if (null == user) {
			throw new UserNotFoundException("User Not Found");
		}
		return user;
    }

	/*
	 * This method should be used to save a new User.
	 */
    
    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> userOpt = userRepo.findById(user.getUserId());
		if (userOpt.isPresent()) {
			throw new UserAlreadyExistsException("User already exists");
		} else {
			userRepo.save(user);
			return true;
		}
	}
}
