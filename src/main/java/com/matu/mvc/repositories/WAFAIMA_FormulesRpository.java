package com.matu.mvc.repositories;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.MATU_Client;
import com.matu.mvc.models.WAFAIMA_Formules;
import com.matu.mvc.models.WAFAIMA_Garanties;


public interface WAFAIMA_FormulesRpository extends  JpaRepository<WAFAIMA_Formules, Integer> {
	@Query(nativeQuery = true, value = "select * from WAFAIMA_Formules ")
	List<WAFAIMA_Formules> get_formules();
	@Query(nativeQuery = true, value = "select *  from WAFAIMA_Formules ")
	List<WAFAIMA_Formules> get_formules2();
	@Query(nativeQuery = true, value = "select * from  WAFAIMA_Formules where idformule = :idformule")
	WAFAIMA_Formules  get_formule_by_idformule(@Param("idformule") Integer idformule);
	@Query(nativeQuery = true, value = "select * from WAFAIMA_Formules where idusage='ava_c1' ")
	List<WAFAIMA_Formules> get_formules_ava();
	@Query(nativeQuery = true, value = "select * from WAFAIMA_Formules where idusage='TT'")
	List<WAFAIMA_Formules> get_formules_b1x();
	@Query(nativeQuery = true, value = "select * from WAFAIMA_Formules where idusage='personnel'")
	List<WAFAIMA_Formules> get_formules_personnel();
	@Query(nativeQuery = true, value = "select * from WAFAIMA_Formules where idusage='scolaire'")
	List<WAFAIMA_Formules> get_formules_scolaire();
	@Query(nativeQuery = true, value = "select * from WAFAIMA_Formules where idusage='frontiere'")
	List<WAFAIMA_Formules> get_formules_frontiere();
	@Query(nativeQuery = true, value = "select * from WAFAIMA_Formules where idusage='location'")
	List<WAFAIMA_Formules> get_formules_location();
}
