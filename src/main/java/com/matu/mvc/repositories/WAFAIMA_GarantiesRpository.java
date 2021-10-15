package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import com.javatechie.jpa.dto.OrderResponse;

import com.matu.mvc.models.WAFAIMA_Contrats;
import com.matu.mvc.models.WAFAIMA_Garanties;


public interface WAFAIMA_GarantiesRpository extends  JpaRepository<WAFAIMA_Garanties, Integer> {
	
	
//	  @Query("SELECT new com.javatechie.jpa.dto.OrderResponse(c.NomClient , p.Formule) FROM Customer c JOIN c.products p")
//	    public List<OrderResponse> getJoinInformation();
	
	@Query(nativeQuery = true, value = "select * from WAFAIMA_Garanties where idformule=:idformule")
	List<WAFAIMA_Garanties> get_garanties_by_idformule(@Param("idformule") Integer idformule);

}
