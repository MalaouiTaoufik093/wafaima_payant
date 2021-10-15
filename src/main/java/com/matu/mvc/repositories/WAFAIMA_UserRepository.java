package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.WAFAIMA_Garanties;
import com.matu.mvc.models.WAFAIMA_User;


public interface WAFAIMA_UserRepository extends JpaRepository<WAFAIMA_User, Long> {
	
	WAFAIMA_User findByUsername(String username);
	
	@Query(nativeQuery = true, value = " select u.*,s.raisonsociale as 'site' from wafaima_user u join NtsSites s on s.idsite=u.idsite where id=:id ")
	WAFAIMA_User get_user_by_id(@Param("id") Long id);
	
	

}
