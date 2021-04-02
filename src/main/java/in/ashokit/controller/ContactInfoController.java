package in.ashokit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.Contact;
import in.ashokit.service.ContactService;

@Controller
public class ContactInfoController {

	private ContactService service;

	public ContactInfoController(ContactService service) {
		this.service = service;
	}


	@GetMapping("/load-form")
	public String loadForm(Model model) {
		Contact cobj = new Contact();

		// sending data from controller to ui
		model.addAttribute("contact", cobj);

		// returning logical view name
		return "contact";
	}

	@PostMapping("/saveContact")
	public String handleSubmitBtn(Contact contact, Model model) {
		boolean isSaved = service.saveOrUpdateContact(contact);

		if (isSaved) {
			model.addAttribute("succMsg", "Contact Saved");
		} else {
			model.addAttribute("failMsg", "Failed to save contact");
		}

		return "contact";
	}

	@GetMapping("/view-contacts")
	public String handleViewsContactHyperLink(Model model) {

		List<Contact> allContacts = service.getAllContacts();

		model.addAttribute("contacts", allContacts);
		
		return "contacts-display";
	}

}