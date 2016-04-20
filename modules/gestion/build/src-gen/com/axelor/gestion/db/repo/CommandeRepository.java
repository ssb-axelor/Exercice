package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gestion.db.Commande;

public class CommandeRepository extends JpaRepository<Commande> {

	public CommandeRepository() {
		super(Commande.class);
	}

}
