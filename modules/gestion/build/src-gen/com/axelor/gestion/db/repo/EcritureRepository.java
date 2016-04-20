package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gestion.db.Ecriture;

public class EcritureRepository extends JpaRepository<Ecriture> {

	public EcritureRepository() {
		super(Ecriture.class);
	}

}
