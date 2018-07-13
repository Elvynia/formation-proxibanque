package fr.gtm.project.proxibanque.business;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import fr.gtm.project.proxibanque.Constants;
import fr.gtm.project.proxibanque.entity.Account;
import fr.gtm.project.proxibanque.entity.Message;
import fr.gtm.project.proxibanque.entity.TYPE_CARD;

@Component
public class WithdrawalComponent {

	public Message processCash(Account account, Float amount) {
		Message result = new Message(
				"Retrait accepté, n'oubliez pas vos billets !");
		if (amount > Constants.WITHDRAW_CASH_MAX) {
			result.setError(true);
			result.setContent(
					"La somme demandée dépasse le seuil autorisé (300€)");
		}
		if (amount > account.getBalance()) {
			result.setError(true);
			result.setContent("La somme demandée dépasse le solde du compte ("
					+ account.getBalance() + ")");
		}
		return result;
	}

	public Message processBankCard(Account account, TYPE_CARD type) {
		final Message result = new Message(
				"Retrait accepté, n'oubliez pas votre nouvelle carte !");
		if (account.getBankCard() != null && account.getBankCard()
				.getExpiredOn().isAfter(LocalDate.now())) {
			result.setError(true);
			result.setContent(
					"Impossible d’effectuer le retrait, votre ancienne carte est encore valide");
		}
		return result;
	}

	public Message processCheckBook(Account account) {
		final Message result = new Message(
				"Demande acceptée, chéquier envoyé à l'adresse $$$ !");
		if (account.getCheckBook() != null) {
			LocalDate limit = account.getCheckBook().getReceivedOn()
					.plusMonths(3).plusDays(1);
			if (LocalDate.now().isBefore(limit)) {
				result.setError(true);
				result.setContent(
						" Impossible d’effectuer le retrait d’un nouveau chéquier pour ce compte avant le "
								+ limit);
			}
		}
		return result;
	}

}
