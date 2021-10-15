package com.matu.mvc.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

//import com.javatechie.jpa.dto.OrderResponse;

import com.matu.mvc.models.MATU_Client;
import com.matu.mvc.models.WAFAIMA_Contrats;
import com.matu.mvc.models.WAFAIMA_Garanties;

@CrossOrigin("*")
public interface WAFAIMA_ContratsRpository extends  JpaRepository<WAFAIMA_Contrats, Integer> {

	
	 @Modifying
	 @Query(value = "insert into wafaima_contrats ("
	    		+ "          datedebut\r\n" + 
	    		"           ,datefin\r\n" + 
	    		"           ,idclient\r\n" + 
	    		"           ,idavenant\r\n" + 
	    		"           ,idformule\r\n" + 
	    		"           ,idhistorique\r\n" + 
	    		"           ,matricule\r\n" + 
	    		"           ,nom_client\r\n" + 
	    		"           ,police\r\n" + 
	    		"           ,usage,periode_assure,montantttc89_92,montantttc93_184,montantttc184,date_creation,idquittance) VALUES (:date_debut,:date_fin,:idclient,:id_avenant,:idformule,:id_historique,:matricule,:nom_client,:police,:usage,:periode_assure,:montantttc89_92,:montantttc93_184,:montantttc184,:date_creation,:idquittance)", nativeQuery = true)
	    @Transactional
	    void save_contrat(
	    		@Param("date_debut") String date_debut,
	    		@Param("date_fin") String date_fin, 
	    		@Param("idclient") Integer idclient, 
	    		@Param("id_avenant") Integer id_avenant, 
	    		@Param("idformule") Integer id_formule,
	    	    @Param("id_historique") Integer id_historique,
	    	    @Param("matricule") String matricule,
	    	    @Param("nom_client") String nom_client,  
	    	    @Param("police") String police, 
	    	    @Param("usage") String usage,
	    	    @Param("periode_assure") Integer periode_assure,
	    	    @Param("montantttc89_92") double montantttc89_92,
	    	    @Param("montantttc93_184") double montantttc93_184,
	    	    @Param("montantttc184") double montantttc184,
	    	    @Param("date_creation") String date_creation,
	    	    @Param("idquittance") Integer idquittance
	    		);
	
	
	@Query(nativeQuery = true, value = "select distinct \r\n" + 
			"			                         id_contrat=c.id_contrat \r\n" + 
			"								    , idhistorique=h.idhistorique \r\n" + 
			"									,h.matricule \r\n" + 
			"									,nom_client = (select raisonsociale from stdclients where idclient = h.idassure),idclient=h.idclient \r\n" + 
			"									,adresse = (select adresse1 + ',' + adresse2 from stdclients where idclient = h.idassure),idclient=h.idclient \r\n" + 
			"									,ville_client = (select libelle from StdVilles where idville =(select distinct idville from StdClients where idclient =h.IdClient)) \r\n" + 
			"						            ,tel_client = (select telep1 from stdclients where idclient = h.idassure)  \r\n" + 
			"									,datedebut=convert(varchar(10),DATE_effet,103) \r\n" + 
			"									,datefin=convert(varchar(10),DATE_AU,103) \r\n" + 
			"									,usage=(select libelle from OtoCarrosseries where IdCarrosserie = h.IdCarrosserie) \r\n" + 
 			"                                   ,idscat=(select IDSCAT from OtoCarrosseries where IdCarrosserie = h.IdCarrosserie) \r\n" +
			"									,idavenant=h.idavenant,avenant = (select libelle from otoavenants where idavenant=h.idavenant) \r\n" + 
			"									,h.police \r\n" + 
			"									,attestation=h.attestation \r\n" + 
			"									,puissance=h.puissance,tonnage=h.tonnage,marque=(select libelle from otomarques where idmarque = h.idmarque) \r\n" + 
			"                                   ,idformule=c.idformule,formule = f.Formule,montantttc184 = f.montantttc184,montantTTC93_184=f.montantTTC93_184,montantTTC89_92=f.montantTTC89_92  \r\n" + 
			"									,nb_places= h.places \r\n" + 
			"                                   ,periode_assure=DATEDIFF(DAY,DATE_EFFET,DATE_AU)" +
			"									,usage_formule=f.libelle_usage,montantttc184 = f.montantttc184\r\n" + 
			"                                   ,categorie_vehicule=(select libelle from OtoCarrosseries where IdCarrosserie = h.IdCarrosserie)\r\n" + 
			"									,datemise=convert(varchar(10),h.DATE_MEC,103) \r\n"+
		    "                                   ,c.idquittance,c.date_creation,essence=case when h.Essence=1 then 'Essence' when h.Essence=2 then 'Diesel' end " + 
			"									 from OtoHistorique h \r\n" + 
			"									 join ntssites s on s.idsite=h.idsite \r\n" + 
			"									 join WAFAIMA_Contrats c on c.idhistorique = h.idhistorique \r\n" + 
			"									 join WAFAIMA_Formules f on   f.idformule = c.idformule \r\n" + 
			"									 where convert(varchar(10),DATE_AU,103)>getdate() \r\n" + 
			"									 and h.IdAnnulHist=0 and h.idsite=:idsite order by id_contrat desc" + 
			" ")
	List<WAFAIMA_Contrats> clients_ayant_offers(@Param("idsite") int idsite);
	
	@Query(nativeQuery = true, value = "select distinct                                \r\n" + 
			"id_contrat=c.id_contrat  \r\n" + 
			", idhistorique=h.idhistorique  \r\n" + 
			",h.matricule  \r\n" + 
			",nom_client = (select raisonsociale from stdclients where idclient = h.idassure),idclient=h.idclient  \r\n" + 
			",adresse = (select adresse1 + ',' + adresse2 from stdclients where idclient = h.idassure),idclient=h.idclient  \r\n" + 
			",ville_client = (select libelle from StdVilles where idville =(select distinct idville from StdClients where idclient =h.IdClient))  \r\n" + 
			",tel_client = (select telep1 from stdclients where idclient = h.idassure)   \r\n" + 
			",datedebut=convert(varchar(10),DATE_effet,103)  \r\n" + 
			",datefin=convert(varchar(10),DATE_AU,103)  \r\n" + 
			",usage=(select libelle from OtoCarrosseries where IdCarrosserie = h.IdCarrosserie)  \r\n" + 
			",idscat=(select IDSCAT from OtoCarrosseries where IdCarrosserie = h.IdCarrosserie) \r\n" +
			",periode_assure=DATEDIFF(DAY,DATE_EFFET,DATE_AU)" +
			",idavenant=h.idavenant,avenant = (select libelle from otoavenants where idavenant=h.idavenant)  \r\n" + 
			",h.police  \r\n" + 
			",attestation=h.attestation  \r\n" + 
			",puissance=h.puissance,tonnage=h.tonnage,marque=(select libelle from otomarques where idmarque = h.idmarque)  \r\n" + 
			",idformule=c.idformule,formule = f.Formule,montantttc184 = f.montantttc184,montantTTC93_184=f.montantTTC93_184,montantTTC89_92=f.montantTTC89_92  \r\n" + 
			",nb_places= h.places  \r\n" + 
			",usage_formule=f.libelle_usage \r\n" + 
			",categorie_vehicule=(select libelle from OtoCarrosseries where IdCarrosserie = h.IdCarrosserie) \r\n" + 
			",datemise=convert(varchar(10),h.DATE_MEC,103)\r\n" + 
			",c.idquittance,c.date_creation,essence=case when h.Essence=1 then 'Essence' when h.Essence=2 then 'Diesel' end   \r\n" + 
			"from OtoHistorique h  \r\n" + 
			"join ntssites s on s.idsite=h.idsite  \r\n" + 
			"join WAFAIMA_Contrats c on c.idhistorique = h.idhistorique  \r\n" + 
			"join WAFAIMA_Formules f on   f.idformule = c.idformule  \r\n" + 
			"where convert(varchar(10),DATE_AU,103)>getdate()  \r\n" + 
			"and h.IdAnnulHist=0 and h.IdHistorique=:idhistorique ")
	WAFAIMA_Contrats affaire_formule(@Param("idhistorique") Integer idhistorique);
	

	@Query(nativeQuery = true, value = "select id_contrat from wafaima_contrats where idclient =:idclient ")
	Integer get_contrat_by_idclient(@Param("idclient") Integer idclient);

	

//	@Query(nativeQuery = true, value = "\r\n" + 
//			"select f.formule,count(c.idformule) as 'nb' from wafaima_contrats c join wafaima_formules f on f.idformule = c.idformule\r\n" + 
//			"group by f.formule,c.idformule ")
//	List<Wafaima_statistique> get_statistiques();
//	@Query(nativeQuery = true, value = "select id_contrat from wafaima_contrats where idclient =:idclient ")
//	Integer get_contrat_by_idclient(@Param("idclient") Integer idclient);
//	

} 
