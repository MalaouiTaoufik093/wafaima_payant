package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

//import com.javatechie.jpa.dto.OrderResponse;

import com.matu.mvc.models.MATU_Client;
import com.matu.mvc.models.WAFAIMA_Contrats;

@CrossOrigin("http://172.20.40.64:9091")
public interface MATU_clientRpository extends  JpaRepository<MATU_Client, Integer> {
	
	
//	  @Query("SELECT new com.javatechie.jpa.dto.OrderResponse(c.NomClient , p.Formule) FROM Customer c JOIN c.products p")
//	    public List<OrderResponse> getJoinInformation();
	
	@Query(nativeQuery = true, value = "select distinct\r\n" + 
			"idaffaire=idhistorique\r\n" + 
			",matricule\r\n" + 
			",client = (select raisonsociale from stdclients where idclient = h.idassure)\r\n" + 
			",effet=convert(varchar(10),DATE_effet,103)\r\n" + 
			",expiration=convert(varchar(10),DATE_AU,103)\r\n" +
			",periode_assure=DATEDIFF(DAY,DATE_EFFET,DATE_AU)" +
			",usage=(select libelle from OtoCarrosseries where IdCarrosserie = h.IdCarrosserie)\r\n" +
			",idscat=(select IDSCAT from OtoCarrosseries where IdCarrosserie = h.IdCarrosserie) \r\n" +
			",avenant = (select libelle from otoavenants where idavenant=h.idavenant)\r\n" +
			",police\r\n"+
			",idclient,id_avenant=h.idavenant\r\n"+
			",nb_place= h.places  \r\n" + 
			",attestation=attestation\r\n"+
			",puissance=h.puissance,tonnage=h.tonnage,marque=(select libelle from otomarques where idmarque = h.idmarque)\r\n" + 
			",essence=case when h.Essence=1 then 'Essence' when h.Essence=2 then 'Diesel' end   \r\n" + 
			",datemise=convert(varchar(10),h.DATE_MEC,103)\r\n" + 
			" from OtoHistorique h join ntssites s on s.idsite=h.idsite where  \r\n" + 
			"  convert(varchar(10),DATE_AU,103)>=getdate()\r\n" +
			"and not h.idhistorique in (select distinct idhistorique from wafaima_contrats )"+
			"  and h.IdAnnulHist=0 and h.idavenant in (1,3) and h.idsite=:idsite ")
	List<MATU_Client> affaires_matu(@Param("idsite") int idsite);
	
	
	@Query(nativeQuery = true, value = " select distinct \r\n" + 
			"idaffaire=idhistorique  \r\n" + 
			",matricule \r\n" + 
			",client = (select raisonsociale from stdclients where idclient = h.idassure)  \r\n" + 
			",effet=convert(varchar(10),DATE_effet,103) \r\n" + 
			",expiration=convert(varchar(10),DATE_AU,103) \r\n" +
			",periode_assure=DATEDIFF(DAY,DATE_EFFET,DATE_AU)" +
			",carrosserie=(select libelle from OtoCarrosseries where IdCarrosserie = h.IdCarrosserie) \r\n" +
			",idscat=(select IDSCAT from OtoCarrosseries where IdCarrosserie = h.IdCarrosserie) \r\n" +
			",usage=AUSAGE\r\n" + 
			",avenant = (select libelle from otoavenants where idavenant=h.idavenant) \r\n" + 
			",police,id_avenant=h.idavenant \r\n" + 
			",idclient \r\n" + 
			
			",attestation=attestation \r\n" + 
			",nb_place= h.places  \r\n" + 
			",puissance=h.puissance,tonnage=h.tonnage,marque=(select libelle from otomarques where idmarque = h.idmarque) \r\n" + 
			",datemise=convert(varchar(10),h.DATE_MEC,103) \r\n" + 
			",essence=case when h.Essence=1 then 'Essence' when h.Essence=2 then 'Diesel' end   \r\n" + 
			"from OtoHistorique h join ntssites s on s.idsite=h.idsite where   \r\n" + 
			"convert(varchar(10),DATE_AU,103)>getdate() \r\n" + 
			"and h.IdAnnulHist=0 and idhistorique  =:idhistorique ")
	MATU_Client  affaire(@Param("idhistorique") Integer idhistorique);
	

}
