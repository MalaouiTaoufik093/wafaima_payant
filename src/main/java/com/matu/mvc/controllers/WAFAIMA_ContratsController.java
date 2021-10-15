package com.matu.mvc.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Info_Client_Facture;
import com.matu.mvc.models.StdQuittances;
import com.matu.mvc.models.WAFAIMA_Contrats;
import com.matu.mvc.models.WAFAIMA_Formules;
import com.matu.mvc.models.WAFAIMA_Garanties;
import com.matu.mvc.models.WAFAIMA_Quittances;
import com.matu.mvc.models.WAFAIMA_User;
import com.matu.mvc.repositories.Details_Facture_Repository;
import com.matu.mvc.repositories.Info_Client_Detail_Repository;
import com.matu.mvc.repositories.StdQuittancesRepository;
import com.matu.mvc.repositories.WAFAIMA_ContratsRpository;
import com.matu.mvc.repositories.WAFAIMA_FormulesRpository;
import com.matu.mvc.repositories.WAFAIMA_GarantiesRpository;
import com.matu.mvc.repositories.WAFAIMA_UserRepository;
import com.matu.mvc.repositories.wafaima_quittances_repository;
import com.matu.mvc.services.Contrat_service;
import com.matu.mvc.services.Facture;
import com.matu.mvc.services.FileHandelService;
import com.matu.mvc.services.UserPrincipal;

@Controller
public class WAFAIMA_ContratsController {
	
	private  Contrat_service contrat_service;
    private FileHandelService fileHandelService;
    private  ServletContext context;
	@Autowired
	WAFAIMA_ContratsRpository contratrepository;
	@Autowired
	WAFAIMA_GarantiesRpository garantiesrepository;
	@Autowired
	WAFAIMA_GarantiesRpository gr;
	@Autowired
	WAFAIMA_FormulesRpository fr;
//	@Autowired
//	WAFAIMA_UserRepository userrepository;
	@Autowired
	private wafaima_quittances_repository wafaima_quittances_repository;
	@Autowired 
	private StdQuittancesRepository  stdQuittances;
	@Autowired 
	private WAFAIMA_FormulesRpository  wafaimaformules;
	@Autowired
	Facture facture;
	@Autowired
	Details_Facture_Repository Test_Repository;
	@Autowired
	Info_Client_Detail_Repository info;
	
    @Autowired
    public WAFAIMA_ContratsController(Contrat_service contrat_service, FileHandelService fileHandelService, ServletContext context) {
        this.contrat_service = contrat_service;
        this.fileHandelService = fileHandelService;
        this.context = context;
    }
    
    @GetMapping(value = "/statistiques")
    public String dashboard(Model model) {
        return "WAFAIMA_dashbord";
    }
    
    @GetMapping(value = "/")
    public String allEmployee(Model model) {
        List<WAFAIMA_Garanties> garanties =gr.get_garanties_by_idformule(10);
        List<WAFAIMA_Formules> formules =fr.get_formules();
        List<WAFAIMA_Formules> formules_ava =fr.get_formules_ava();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser.getlibelle();
        model.addAttribute("garanties",garanties);
        model.addAttribute("formules",formules);
        model.addAttribute("formules_ava",formules_ava);
        model.addAttribute("libelle_user",libelle_user);
        return "WAFAIMA_Contrats2";
    }
    
    

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    
    
    @GetMapping(value = "/offers_clients")
    public String AllClients_Offers(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
        List<WAFAIMA_Contrats> offers_clients = contratrepository.clients_ayant_offers(idsite);
        List<WAFAIMA_Garanties> garanties_formule = garantiesrepository.get_garanties_by_idformule(2);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser2 = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser2.getlibelle();
        model.addAttribute("offers_clients", offers_clients);
        model.addAttribute("libelle_user",libelle_user);

        return "WAFAIMA_Clients_Ayant_Offre";
    }
    
    @GetMapping(value = "/all_contrats")
    public String allEmployee2(Model model) {
//    	    model.addAttribute("personForm", form);
        List<WAFAIMA_Contrats> employees = contrat_service.get_clients_wafa_ima();
        model.addAttribute("assures", employees);
//        return "view/employee";
        return "assure";
    }	
//	@GetMapping("/index")
//	public String index() {
//		return "index";
//	}
    //@CrossOrigin("*")
    @GetMapping(value = "/pdf_contrat/{id}/{id_formule}")
    public void allPdf(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id,@PathVariable Integer id_formule) {
//        List<WAFAIMA_Contrats> affaires_wafaima = (List<WAFAIMA_Contrats>) contrat_service.get_clients_wafa_ima();
    	WAFAIMA_Contrats affaire = contrat_service.get_client_by_ida_ffaire(id);
    	List<WAFAIMA_Garanties>  garanties = garantiesrepository.get_garanties_by_idformule(id_formule);
        boolean isFlag = contrat_service.createPdf(affaire,garanties, context, request, response);
        if (isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".pdf");
            fileHandelService.filedownload(fullPath, response, "employees.pdf");
        }
    }
    
    @GetMapping(value = "/pdf/{num_facture}")
    public void allPdf(HttpServletRequest request, HttpServletResponse response,@PathVariable String num_facture) {
    	List<Details_facture>  quittances = Test_Repository.details_facture(num_facture);
    	Info_Client_Facture info_client = info.info_client_detail(num_facture);
    	boolean isFlag = facture.createPdf(quittances,info_client, context, request, response);
        if (isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".pdf");
            fileHandelService.filedownload(fullPath, response, "employees.pdf");
        }
    } 
    
    
    @GetMapping(value = "/excel_contrats")
    public void allExcel(HttpServletRequest request, HttpServletResponse response) {
        List<WAFAIMA_Contrats> contrats = contrat_service.get_clients_wafa_ima();
        boolean isFlag = contrat_service.createExcell(contrats, context, request, response);
        if (isFlag){
            String fullPath = request.getServletContext().getRealPath("/resources/report/" + "contrats" + ".xls");
            fileHandelService.filedownload(fullPath, response,"contrats.xls");
        }
    }
	@PostMapping("/save_contrat")
	public String save(WAFAIMA_Contrats e,StdQuittances qt) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		SimpleDateFormat dateFormat2=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Integer new_quittance = stdQuittances.get_quittance_from_stdnemeros();
		WAFAIMA_Formules f = wafaimaformules.get_formule_by_idformule(e.getIdformule());
		double  montantttc184= 0,montantttc93_184= 0,montantttc89_92= 0,PNette=0,Taxe=0,commission=0,tva=0,NetCie=0,PTotal=0;
		if(e.getPeriode_assure() > 184) {montantttc184=f.getMontantTTC184();montantttc93_184=f.getMontantTTC93_184();montantttc89_92=f.getMontantTTC89_92();PNette=f.getMontantHT184();Taxe=f.getMontantTTC184() - f.getMontantHT184();commission=f.getFraisGest184();tva=f.getTVA184();NetCie=f.getMontantHT184();PTotal=f.getMontantTTC184();}
		if(e.getPeriode_assure()  >= 93 && e.getPeriode_assure() <= 184) {montantttc184=f.getMontantTTC184();montantttc93_184=f.getMontantTTC93_184();montantttc89_92=f.getMontantTTC89_92();PNette=f.getMontantHT93_184();Taxe=f.getMontantTTC93_184() - f.getMontantHT93_184();commission=f.getFraisGest93_184();tva=f.getTVA93_184();NetCie=f.getMontantHT93_184();PTotal=f.getMontantTTC93_184();}
		if(e.getPeriode_assure()  >= 89 && e.getPeriode_assure() <= 92 ) {montantttc184=f.getMontantTTC184();montantttc93_184=f.getMontantTTC93_184();montantttc89_92=f.getMontantTTC89_92();PNette=f.getMontantTTC89_92();Taxe=f.getMontantTTC89_92() - f.getMontantHT89_92();commission=f.getFraisGest89_92();tva=f.getTVA89_92();NetCie=f.getMontantHT89_92();PTotal=f.getMontantTTC89_92();}
        stdQuittances.save_quittance(idsite,year, new_quittance,e.getPolice(),e.getIdclient(), "T", e.getDate_debut(), e.getDate_fin(), 3,1,PNette,Taxe,0,0,commission,tva,"N",1,"N","N",dateFormat2.format(date),0,PTotal,PTotal,e.getIdclient());
        stdQuittances.update_quittance_stdnumeros(new_quittance+1);
        Integer idquittance = stdQuittances.get_last_idquittance_inserted();
		contratrepository.save_contrat(e.getDate_debut(),e.getDate_fin(), e.getIdclient(), e.getId_avenant(), e.getIdformule(), e.getId_historique(), e.getMatricule(), e.getNom_client(), e.getPolice(), e.getUsage(),e.getPeriode_assure(),montantttc89_92,montantttc93_184,montantttc184,dateFormat2.format(date),idquittance);  
		wafaima_quittances_repository.save_quittances(dateFormat.format(date), "O",e.getIdclient(),idsite,e.getIdformule(),contratrepository.get_contrat_by_idclient(e.getIdclient()));
		return "redirect:/pdf_contrat/"+e.getId_historique()+'/'+ e.getIdformule();
	}	 
//	@RequestMapping(value="/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})	
	@GetMapping("/delete_contrat/{id}")
	public String delete(@PathVariable Integer id) {
		contratrepository.deleteById(id);
		return "redirect:/";
	}

}
