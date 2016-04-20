package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gestion.db.LigneEcriture;

public class LigneEcritureRepository extends JpaRepository<LigneEcriture> {

	public LigneEcritureRepository() {
		super(LigneEcriture.class);
	}

}
