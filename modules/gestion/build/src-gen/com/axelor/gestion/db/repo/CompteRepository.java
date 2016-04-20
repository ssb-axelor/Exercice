package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.gestion.db.Compte;

public class CompteRepository extends JpaRepository<Compte> {

	public CompteRepository() {
		super(Compte.class);
	}

	public Compte findByCode(String code) {
		return Query.of(Compte.class)
				.filter("self.code = :code")
				.bind("code", code)
				.fetchOne();
	}

	public Compte findByLibelle(String libelle) {
		return Query.of(Compte.class)
				.filter("self.libelle = :libelle")
				.bind("libelle", libelle)
				.fetchOne();
	}

}
