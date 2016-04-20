package com.axelor.gestion.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gestion.db.Client;

public class ClientRepository extends JpaRepository<Client> {

	public ClientRepository() {
		super(Client.class);
	}

}
