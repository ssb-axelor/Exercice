package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gestion.db.HelloGestion;

public class HelloGestionRepository extends JpaRepository<HelloGestion> {

	public HelloGestionRepository() {
		super(HelloGestion.class);
	}

}
