package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gestion.db.FamilleProduit;

public class FamilleProduitRepository extends JpaRepository<FamilleProduit> {

	public FamilleProduitRepository() {
		super(FamilleProduit.class);
	}

}
