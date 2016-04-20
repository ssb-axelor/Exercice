package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gestion.db.LigneFacture;

public class LigneFactureRepository extends JpaRepository<LigneFacture> {

	public LigneFactureRepository() {
		super(LigneFacture.class);
	}

}
