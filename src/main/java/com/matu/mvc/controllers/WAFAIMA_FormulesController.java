package com.matu.mvc.controllers;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.matu.mvc.models.WAFAIMA_Contrats;
import com.matu.mvc.models.WAFAIMA_Formules;
import com.matu.mvc.models.WAFAIMA_Garanties;
import com.matu.mvc.repositories.WAFAIMA_ContratsRpository;
import com.matu.mvc.repositories.WAFAIMA_FormulesRpository;
import com.matu.mvc.repositories.WAFAIMA_GarantiesRpository;
//import com.matu.mvc.repositories.EmployerRpository;
//import com.matu.mvc.services.EmployeeService_old;
import com.matu.mvc.services.FileHandelService;
import com.matu.mvc.services.UserPrincipal;

@Controller
public class WAFAIMA_FormulesController {
	
	@Autowired
	WAFAIMA_FormulesRpository formules_repository;
	@Autowired
	WAFAIMA_GarantiesRpository garanties_repository;
	
    @GetMapping(value = "/formules")
    public String allEmployee(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser.getlibelle();
        List<WAFAIMA_Formules> employees = formules_repository.findAll();
        List<WAFAIMA_Formules> formules2 = formules_repository.findAll();
        List<WAFAIMA_Formules> formules_ava_c1 = formules_repository.get_formules_ava();
        List<WAFAIMA_Formules> formules_b1x = formules_repository.get_formules_b1x();
        List<WAFAIMA_Formules> formules_personnel = formules_repository.get_formules_personnel();
        List<WAFAIMA_Formules> formules_scolaire = formules_repository.get_formules_scolaire();
        List<WAFAIMA_Formules> formules_frontiere = formules_repository.get_formules_frontiere();
        List<WAFAIMA_Formules> formules_location = formules_repository.get_formules_location();
        
        List<WAFAIMA_Garanties> garanties_essentiel = garanties_repository.get_garanties_by_idformule(1);
        List<WAFAIMA_Garanties> garanties_sernite = garanties_repository.get_garanties_by_idformule(2);
        List<WAFAIMA_Garanties> garanties_privilege = garanties_repository.get_garanties_by_idformule(3);
        List<WAFAIMA_Garanties> garanties_silver = garanties_repository.get_garanties_by_idformule(4);
        List<WAFAIMA_Garanties> garanties_gold = garanties_repository.get_garanties_by_idformule(5);

        List<WAFAIMA_Garanties> garanties_personnel_silver = garanties_repository.get_garanties_by_idformule(17);
        List<WAFAIMA_Garanties> garanties_personnel_gold = garanties_repository.get_garanties_by_idformule(18);
        List<WAFAIMA_Garanties> garanties_scolaire_silver = garanties_repository.get_garanties_by_idformule(19);
        List<WAFAIMA_Garanties> garanties_frontiere = garanties_repository.get_garanties_by_idformule(20);
        List<WAFAIMA_Garanties> garanties_location = garanties_repository.get_garanties_by_idformule(21);

        model.addAttribute("formules", employees);
        model.addAttribute("formules2", formules2);
        model.addAttribute("libelle_user",libelle_user);
        model.addAttribute("garanties_essentiel", garanties_essentiel);
        model.addAttribute("garanties_sernite", garanties_sernite);
        model.addAttribute("garanties_privilege", garanties_privilege);
        model.addAttribute("garanties_silver", garanties_silver);
        model.addAttribute("garanties_gold", garanties_gold);
        model.addAttribute("garanties_personnel_silver", garanties_personnel_silver);
        model.addAttribute("garanties_personnel_gold", garanties_personnel_gold);
        model.addAttribute("garanties_scolaire_silver", garanties_scolaire_silver);
        model.addAttribute("garanties_frontiere", garanties_frontiere);
        model.addAttribute("garanties_location", garanties_location);
        
        
        
        
        model.addAttribute("formules_ava_c1", formules_ava_c1);
        model.addAttribute("formules_b1x", formules_b1x);
        model.addAttribute("formules_personnel", formules_personnel);
        model.addAttribute("formules_scolaire", formules_scolaire);
        model.addAttribute("formules_frontiere", formules_frontiere);
        model.addAttribute("formules_location", formules_location);
        
        
        return "WAFAIMA_Formules";
    }
    
  

}
