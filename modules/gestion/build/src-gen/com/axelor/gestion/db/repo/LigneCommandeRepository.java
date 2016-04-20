package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gestion.db.LigneCommande;

public class LigneCommandeRepository extends JpaRepository<LigneCommande> {

	public LigneCommandeRepository() {
		super(LigneCommande.class);
	}

}
