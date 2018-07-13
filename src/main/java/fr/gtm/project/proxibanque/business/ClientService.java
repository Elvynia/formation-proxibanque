package fr.gtm.project.proxibanque.business;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gtm.project.proxibanque.entity.Client;

@Service
public class ClientService extends CrudService<Client> {

	@Autowired
	private ObjectFactory<SearchClientComponent> searchClientFactory;
	private Map<Integer, SearchClientComponent> searches;

	public ClientService() {
		this.searches = new HashMap<>();
	}

	public Integer findClient(final String search) {
		Integer searchId = null;
		SearchClientComponent scc = this.searchClientFactory.getObject();
		scc.setSearch(search);
		if (scc.searchValid()) {
			searchId = scc.getId();
			this.searches.put(scc.getId(), scc);
		}
		return searchId;
	}

	public Client validateClient(final Integer searchId,
			final LocalDate birthDate) {
		return this.searches.get(searchId).getValidatedClient(birthDate);
	}
}
