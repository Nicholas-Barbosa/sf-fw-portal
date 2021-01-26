package com.faraway.fwportal.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.service.ConhecimentoCrdService;

@Controller
@RequestMapping("/conhecimentos")
public class ConhecimentoController {

	private final ConhecimentoCrdService conhecimentoCrudService;

	public ConhecimentoController(ConhecimentoCrdService conhecimentoCrudService) {
		super();
		this.conhecimentoCrudService = conhecimentoCrudService;
	}

	@RequestMapping("/findByRemetente")
	public String returnPageFindByRemetente(Model model) {
		return "conhecimento/byRemetente";
	}

	@RequestMapping(value = "/findByRemetente/{cnpj}")
	public String findByRemetente(Model model, @PathVariable("cnpj") String cnpj,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(10);

		Pageable pageParam = PageRequest.of(currentPage, pageSize);
		Page<Conhecimento> conhecimentos = conhecimentoCrudService.findByRemetenteThreeMonths(cnpj, pageParam)
				.map(Conhecimento::new);
		model.addAttribute("conhecimentos", conhecimentos);
		System.out.println("CNPJ " + cnpj);
		int totalPages = conhecimentos.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "conhecimento/byRemetente";

	}
}
