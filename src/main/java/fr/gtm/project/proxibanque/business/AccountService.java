package fr.gtm.project.proxibanque.business;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gtm.project.proxibanque.entity.Account;
import fr.gtm.project.proxibanque.entity.Client;
import fr.gtm.project.proxibanque.entity.Message;
import fr.gtm.project.proxibanque.entity.TYPE_CARD;
import fr.gtm.project.proxibanque.entity.TYPE_COMPTE;

@Service
public class AccountService extends CrudService<Account> {

	@Autowired
	private WithdrawalComponent withdrawalComp;

	@Autowired
	private AccountComponent accountComp;

	public Message transferInternal(Integer idAccount, Float amount) {
		// TODO
		return new Message("Virement effectué avec succès !");
	}

	public Message withdrawCash(Integer idAccount, Float amount) {
		final Account account = this.read(idAccount);
		final Message result = this.withdrawalComp.processCash(account, amount);
		if (!result.isError()) {
			account.setBalance(account.getBalance() - amount);
			this.edit(account);
		}
		return result;
	}

	public Message withdrawBankCard(Integer idAccount, TYPE_CARD type) {
		return this.withdrawalComp.processBankCard(this.read(idAccount), type);
	}

	public Message withdrawCheckBook(Integer idAccount) {
		return this.withdrawalComp.processCheckBook(this.read(idAccount));
	}

	public Message create(Float balance, String label, String number,
			Client client) {
		final Account account = new Account();
		account.setBalance(balance);
		account.setLabel(label);
		account.setNumber(number);
		account.setType(TYPE_COMPTE.MAIN);
		account.setOpenedOn(LocalDate.now());
		final Message result = this.accountComp
				.validateAccountCreation(account);
		if (!result.isError()) {
			account.setClient(client);
			this.create(account);
		}
		return result;
	}
}
