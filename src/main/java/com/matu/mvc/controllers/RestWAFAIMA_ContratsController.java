package com.matu.mvc.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matu.mvc.models.MATU_Client;
import com.matu.mvc.models.Nb_Formules;
import com.matu.mvc.models.OrderResponse;
import com.matu.mvc.models.WAFAIMA_Contrats;
import com.matu.mvc.models.WAFAIMA_Formules;
import com.matu.mvc.models.WAFAIMA_Garanties;
import com.matu.mvc.models.WAFAIMA_User;
import com.matu.mvc.repositories.MATU_clientRpository;
import com.matu.mvc.repositories.Nb_Formules_Repository;
import com.matu.mvc.repositories.WAFAIMA_ContratsRpository;
import com.matu.mvc.repositories.WAFAIMA_FormulesRpository;
import com.matu.mvc.repositories.WAFAIMA_GarantiesRpository;
import com.matu.mvc.repositories.WAFAIMA_UserRepository;
import com.matu.mvc.services.UserPrincipal;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://172.20.40.64:9091")
public class RestWAFAIMA_ContratsController   {

	@Autowired
	WAFAIMA_ContratsRpository contratrepository;

	@Autowired
	MATU_clientRpository mr;
	@Autowired
	WAFAIMA_GarantiesRpository garantierepository;
	@Autowired
	WAFAIMA_FormulesRpository fr;
	@Autowired
	WAFAIMA_UserRepository userrepository;
	@Autowired
	Nb_Formules_Repository nb_Formules_Repository;
	
	
	
	@GetMapping("/test")
	public void test() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle = customUser.getlibelle();
		
//		WAFAIMA_User user = userrepository.get_user_by_id(iduser);
		System.out.println(libelle);
	}
	@GetMapping("/nb_formules_ava")
	public List<Nb_Formules> nb_formules_ava() {
		return nb_Formules_Repository.get_nb_formules_ava();
	}
	@GetMapping("/nb_formules_tt")
	public List<Nb_Formules> nb_formules_tt() {
		return nb_Formules_Repository.get_nb_formules_tt();
	}
	@GetMapping("/WAFAIMA_contrats")
	public List<MATU_Client> employes() {;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		return mr.affaires_matu(idsite);
	}

	@GetMapping("/clients_ayant_offers")
	public List<WAFAIMA_Contrats> clients_ayant_offer() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		return contratrepository.clients_ayant_offers(idsite);
		// return fr.get_formules();
	}

	@GetMapping("/garanties")
	public List<WAFAIMA_Garanties> garanties() {
		return garantierepository.findAll();
	}

	@GetMapping("/garanties/{id}")
	public List<WAFAIMA_Garanties> garanties_by_id_formule(@PathVariable Integer id) {
		return garantierepository.get_garanties_by_idformule(id);
	}

	@GetMapping("/affaire_formule/{id}")
	public WAFAIMA_Contrats get_info_affaire_by_id_affaire(@PathVariable Integer id) {
		return contratrepository.affaire_formule(id);
	}

	@CrossOrigin("http://172.20.40.64:9091")
	@GetMapping("/affaire/{id}")
	public com.matu.mvc.models.MATU_Client MATU_Client(@PathVariable Integer id) {
//    	return er.findById(id);
		return mr.affaire(id);
	}

//    @RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})	
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable Integer Id2) {
//    	er.deleteById(Id2);
//    	return "redirect:/";
//    }

//    @GetMapping("/delete/{id}")
//    public Employer delete(@PathVariable Integer id2) {
//    	return er.deleteById(id2);
////		return "redirect:/";
//    }

}
