package fr.gtm.project.proxibanque.business;

import org.springframework.stereotype.Component;

import fr.gtm.project.proxibanque.entity.Account;
import fr.gtm.project.proxibanque.entity.Message;

@Component
public class AccountComponent {

	public Message validateAccountCreation(Account account) {
		final Message result = new Message(
				"Le nouveau compte a bien été créé !");
		// TODO
		return result;
	}
}
