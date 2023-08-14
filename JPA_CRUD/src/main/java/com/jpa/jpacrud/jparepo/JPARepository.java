package com.jpa.jpacrud.jparepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jpa.jpacrud.modal.JPAModal;


@Repository
public interface JPARepository extends JpaRepository<JPAModal,String> {

	
	
}
