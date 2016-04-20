package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gestion.db.Evenement;

public class EvenementRepository extends JpaRepository<Evenement> {

	public EvenementRepository() {
		super(Evenement.class);
	}

}
