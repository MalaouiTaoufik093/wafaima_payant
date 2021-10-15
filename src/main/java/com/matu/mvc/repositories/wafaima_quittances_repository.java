package com.matu.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.matu.mvc.models.WAFAIMA_Quittances;

public interface wafaima_quittances_repository extends JpaRepository<WAFAIMA_Quittances, Integer> {
	
	@Modifying
	 @Query(value = "INSERT INTO wafaima_quittances (dateemis,encaisse,idclient,idsite,idformule,idcontrat) values (:dateemis,:encaisse,:idclient,:idsite,:idformule,:idcontrat)" , nativeQuery = true)
	    @Transactional
	    void save_quittances(@Param("dateemis") String dateemis,@Param("encaisse") String encaisse,@Param("idclient") Integer idclient,@Param("idsite") Integer idsite,@Param("idformule") Integer idformule,@Param("idcontrat") Integer idcontrat);
	
//	@Modifying
//	 @Query(value = "INSERT INTO [dbo].[wafaima_quittances]\r\n" + 
//	 		"           ([dateemis]\r\n" + 
//	 		"           ,[encaisse]\r\n" + 
//	 		"           ,[idclient]\r\n" + 
//	 		"           ,[idsite]\r\n" + 
//	 		"           ,[mt_encaisse]\r\n" + 
//	 		"           ,[mt_fraisgestion]\r\n" + 
//	 		"           ,[mt_total]\r\n" + 
//	 		"           ,[mt_wafaima]\r\n" + 
//	 		"           ,[quittance]\r\n" + 
//	 		"           ,[statut]\r\n" + 
//	 		"           ,[taxe]\r\n" + 
//	 		"           ,[tva])\r\n" + 
//	 		"     VALUES\r\n" + 
//	 		"           :dateemis\r\n" + 
//	 		"		    ,:encaisse,\r\n" + 
//	 		"           ,:idclient,\r\n" + 
//	 		"           ,:idsite,\r\n" + 
//	 		"           ,:mt_encaisse,\r\n" + 
//	 		"           ,:mt_fraisgestion,\r\n" + 
//	 		"           ,:mt_total, \r\n" + 
//	 		"           ,:mt_wafaima,\r\n" + 
//	 		"           ,:quittance,\r\n" + 
//	 		"           ,:statut,\r\n" + 
//	 		"           ,:taxe,\r\n" + 
//	 		"           ,:tva,", nativeQuery = true)
//	    @Transactional
//	    void save_quittances(
//	    		@Param("dateemis") String dateemis,
//	    		@Param("encaisse") String encaisse, 
//	    		@Param("idsite") Integer idsite, 
//	    		@Param("mt_encaisse") Integer mt_encaisse, 
//	    		@Param("mt_fraisgestion") Integer mt_fraisgestion,
//	    	    @Param("mt_total") Integer mt_total,
//	    	    @Param("mt_wafaima") String mt_wafaima,
//	    	    @Param("quittance") String quittance,  
//	    	    @Param("statut") String statut, 
//	    	    @Param("taxe") String taxe,
//	    	    @Param("tva") String tva
//	    		);
	

}
