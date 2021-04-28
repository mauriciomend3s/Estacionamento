package br.ucsal.Estacionamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
	@Autowired
	private ClienteService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Cliente> listClientes = service.listall();
		model.addAttribute("listClientes", listClientes);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewClienteForm(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		
		return "new_cliente";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCliente(@ModelAttribute("cliente") Cliente cliente) {
		service.save(cliente);
		
		return "redirect:/";
}
	@RequestMapping("/edit/{id_cliente}")
	public ModelAndView showEditClienteForm(@PathVariable(name="id_cliente") Long id_cliente) {
		ModelAndView mav = new ModelAndView("edit_cliente"); 
		
	Cliente cliente = service.get(id_cliente);
	mav.addObject("cliente", cliente);
	
	return mav;
}
	@RequestMapping("/delete/{id_cliente}")
	public String deleteCliente(@PathVariable(name = "id_cliente") Long id_cliente) {
		service.delete(id_cliente);
		
		return "redirect:/";
	}
	
	
}