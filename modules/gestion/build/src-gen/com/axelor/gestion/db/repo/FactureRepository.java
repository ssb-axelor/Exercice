package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gestion.db.Facture;

public class FactureRepository extends JpaRepository<Facture> {

	public FactureRepository() {
		super(Facture.class);
	}

}
