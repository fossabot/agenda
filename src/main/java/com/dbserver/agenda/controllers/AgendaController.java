package com.dbserver.agenda.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dbserver.agenda.models.AgendaModel;
import com.dbserver.agenda.repositories.AgendaRepository;

@Controller
public class AgendaController {
	@Autowired
	AgendaRepository agendaRepository;

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelView = new ModelAndView("index");

		Iterable<AgendaModel> contatos = agendaRepository.findAll();
		modelView.addObject("contatos", contatos);

		return modelView;
	}

	@PostMapping("/")
	public String novoContato(AgendaModel agenda) {
		agendaRepository.save(agenda);
		return "redirect:/";
	}
	
	@Transactional
	@GetMapping("/excluir/{id}")
	public String excluirContato(@PathVariable("id") Long id) {
		agendaRepository.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView detalhesContato(@PathVariable("id") Long id) {
		ModelAndView modelView = new ModelAndView("editar");

		Iterable<AgendaModel> contato = agendaRepository.findById(id);
		modelView.addObject("contato", contato);

		return modelView;
	}
	
	@PostMapping("/editar")
	public String editarContato(AgendaModel agenda) {
		agendaRepository.save(agenda);
		return "redirect:/";
	}
}