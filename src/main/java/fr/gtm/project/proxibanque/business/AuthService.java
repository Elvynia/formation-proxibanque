package fr.gtm.project.proxibanque.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.gtm.project.proxibanque.dao.AdvisorDao;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private AdvisorDao repo;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return this.repo.findOneByLogin(username);
	}

}
