package com.Contact.Management.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.Contact.Management.Models.Contact;
import com.Contact.Management.Models.User;
import com.Contact.Management.Repository.ContactRepository;
import com.Contact.Management.Repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired

  private ContactRepository contactRepository;

  // Methode for adding common data for response
  @ModelAttribute
  public void addCommonData(Model model, Principal principal) {
    String userName = principal.getName();
    System.out.println("UserName " + userName);
    User user = userRepository.getUserByUsername(userName);
    System.out.println("User " + user);
    model.addAttribute("user", user);

  }

  @RequestMapping("/index")
  public String index(Model model, Principal principal) {

    return "normal/user_dashboard";
  }

  // open add form handler
  @GetMapping("/add-contact")
  public String openAddContactForm(Model model) {
    model.addAttribute("title", "Add Contact");

    model.addAttribute("contact", new Contact());

    return "normal/add_contact_form";
  }

  // Post in processing add contact
  @PostMapping("/process")
  public String processContact(@ModelAttribute Contact contact, Principal principal) {

    System.out.println("Data" + contact);

    String name = principal.getName();
    User user = this.userRepository.getUserByUsername(name);

    contact.setUser(user);

    user.getContacts().add(contact);

    this.userRepository.save(user);

    System.out.println("Added to database");

    return "normal/add_contact_form";
  }

  // show contact handler
  @GetMapping("/showContact")
  public String showContacts(Model model, Principal principal) {
    // contact ki list ko bhejna
    String userName = principal.getName();
    User user = this.userRepository.getUserByUsername(userName);

    List<Contact> contacts = this.contactRepository.findContactByUser(user.getId());

    model.addAttribute("contacts", contacts);

    return "normal/show_contacts";

  }

  // Delete Contact

  @RequestMapping("/delete/{cId}")
  public String deleteContact(@PathVariable("cId") Integer cId, Model model, Principal principal) {
    Optional<Contact> contactOptional = this.contactRepository.findById(cId);

    if (contactOptional.isPresent()) {
      Contact contact = contactOptional.get();

      String userName = principal.getName();
      User user = this.userRepository.getUserByUsername(userName);

      if (user != null && user.getId() == contact.getUser().getId()) { // Use '==' for primitive int comparison
        this.contactRepository.delete(contact);
      } else {
        model.addAttribute("error", "You are not authorized to delete this contact.");
      }
    } else {
      model.addAttribute("error", "Contact not found.");
    }

    return "redirect:/user/showContact";
  }

  // Update data form open
  @PostMapping("/update_contact/{cId}")
  public String updateForm(@PathVariable("cId") Integer cId, Model model) {

    model.addAttribute("title", "Update Contact");
    Contact contact = this.contactRepository.findById(cId).get();

    model.addAttribute("contact", contact);
    return "normal/update_form";
  }

  @RequestMapping(value = "/process_update", method = RequestMethod.POST)
  public String updateHandler(@ModelAttribute Contact contact, Model model, Principal principal,
      @PathVariable("cId") Integer cId) {
    System.out.println("CONTACT NAME: " + contact.getName());
    System.out.println("CONTACT ID: " + contact.getcId()); // Check if this ID is correct

    User user = this.userRepository.getUserByUsername(principal.getName());

    contact.setUser(user);
    this.contactRepository.save(contact);

    return "redirect:/user/showContact";
  }

}
