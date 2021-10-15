package com.matu.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.matu.mvc.models.WAFAIMA_User;
import com.matu.mvc.repositories.WAFAIMA_UserRepository;

@Service
public class MyUserDetailsService  implements UserDetailsService{

	@Autowired
	WAFAIMA_UserRepository userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
		WAFAIMA_User wAFAIMA_User = userrepository.findByUsername(username);
		
		if (wAFAIMA_User == null) {
			
			throw new UsernameNotFoundException("user non trouvé");
		}
		
		return new UserPrincipal(wAFAIMA_User);
	}

}
