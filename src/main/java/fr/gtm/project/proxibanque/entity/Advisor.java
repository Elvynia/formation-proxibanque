package fr.gtm.project.proxibanque.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Advisor implements Entity, UserDetails {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String login;

	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>();
	}

	@Transient
	@Override
	public String getUsername() {
		return this.login;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}

}
