package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gestion.db.Produit;

public class ProduitRepository extends JpaRepository<Produit> {

	public ProduitRepository() {
		super(Produit.class);
	}

}
