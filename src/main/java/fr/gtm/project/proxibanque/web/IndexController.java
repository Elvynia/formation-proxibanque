package fr.gtm.project.proxibanque.web;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import fr.gtm.project.proxibanque.business.ClientService;
import fr.gtm.project.proxibanque.entity.Client;

@Controller
public class IndexController {

	@Autowired
	private ClientService service;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/disconnect")
	public String disconnectClient(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
	}

	@PostMapping("/index")
	public ModelAndView search(@RequestParam String keywords) {
		final ModelAndView mav = new ModelAndView("index");
		final Integer id = this.service.findClient(keywords);
		if (id != null) {
			mav.addObject("searchId", id);
		} else {
			mav.setViewName("errors/generic");
			mav.addObject("message",
					"Aucun résultat, veuillez vous adresser à un conseiller.");
		}
		return mav;
	}

	@PostMapping("/{id}/validate")
	public ModelAndView validate(@PathVariable("id") Integer searchId,
			@RequestParam("birthdate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate) {
		final ModelAndView mav = new ModelAndView();
		final Client client = this.service.validateClient(searchId, birthDate);
		if (client != null) {
			mav.setViewName("index");
			mav.addObject("clientId", client.getId());
		} else {
			mav.setViewName("errors/generic");
			mav.addObject("message",
					"Aucun résultat, veuillez vous adresser à un conseiller.");
		}
		return mav;
	}
}
