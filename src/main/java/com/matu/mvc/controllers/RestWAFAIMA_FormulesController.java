package com.matu.mvc.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.matu.mvc.models.MATU_Client;
import com.matu.mvc.models.OrderResponse;
import com.matu.mvc.models.WAFAIMA_Contrats;
import com.matu.mvc.models.WAFAIMA_Formules;
import com.matu.mvc.models.WAFAIMA_Garanties;
import com.matu.mvc.repositories.MATU_clientRpository;
import com.matu.mvc.repositories.WAFAIMA_ContratsRpository;
import com.matu.mvc.repositories.WAFAIMA_FormulesRpository;
import com.matu.mvc.repositories.WAFAIMA_GarantiesRpository;

@RestController
@RequestMapping("api/")
public class RestWAFAIMA_FormulesController {
	@Autowired
	WAFAIMA_FormulesRpository fr;
	@Autowired
	WAFAIMA_GarantiesRpository gr;
	@GetMapping("/formules")
	public List<WAFAIMA_Formules> liste_formules() {
		return fr.get_formules();
	}

	
	  @GetMapping("/formules2") public List<WAFAIMA_Formules> liste_formules2() {
	  return fr.get_formules_scolaire(); }
	 
	@GetMapping("/formules_ava")
	public List<WAFAIMA_Formules> liste_formules_ava() {
		return fr.get_formules_ava();
	}
	@GetMapping("/formules_b1x")
	public List<WAFAIMA_Formules> liste_formules_b1x() {
		return fr.get_formules_b1x();
	}
	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping("/formule/{id}")
	public WAFAIMA_Formules get_formele(@PathVariable Integer id) {
		return fr.get_formule_by_idformule(id);
	}
	
	
}
