package com.axelor.app.gestion.db;

public interface ICommande {


	/**
	 * Static status select
	 */

	static final int STATUS_BROUILLON = 1; 
	static final int STATUS_VALIDE = 2;
	static final int STATUS_FACTURE = 3;
}