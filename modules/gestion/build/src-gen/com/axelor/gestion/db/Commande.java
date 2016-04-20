package com.axelor.gestion.db;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.joda.time.LocalDate;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "GESTION_COMMANDE")
public class Commande extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_COMMANDE_SEQ")
	@SequenceGenerator(name = "GESTION_COMMANDE_SEQ", sequenceName = "GESTION_COMMANDE_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	@Index(name = "GESTION_COMMANDE_NOM_IDX")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Client nom;

	@NotNull
	private LocalDate dateCommande;

	@NotNull
	private LocalDate datePrevFacture;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LigneCommande> produits;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal commandeHT = BigDecimal.ZERO;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal commandeTTC = BigDecimal.ZERO;

	@Widget(title = "Statut", readonly = true, selection = "commande.status.select")
	private Integer statusSelect = 1;

	private Boolean factureGeneree = Boolean.FALSE;

	public Commande() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Client getNom() {
		return nom;
	}

	public void setNom(Client nom) {
		this.nom = nom;
	}

	public LocalDate getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(LocalDate dateCommande) {
		this.dateCommande = dateCommande;
	}

	public LocalDate getDatePrevFacture() {
		return datePrevFacture;
	}

	public void setDatePrevFacture(LocalDate datePrevFacture) {
		this.datePrevFacture = datePrevFacture;
	}

	public List<LigneCommande> getProduits() {
		return produits;
	}

	public void setProduits(List<LigneCommande> produits) {
		this.produits = produits;
	}

	/**
	 * Add the given {@link LigneCommande} item to the {@code produits}.
	 *
	 * <p>
	 * It sets {@code item.commande = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addProduit(LigneCommande item) {
		if (produits == null) {
			produits = new ArrayList<LigneCommande>();
		}
		produits.add(item);
		item.setCommande(this);
	}

	/**
	 * Remove the given {@link LigneCommande} item from the {@code produits}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeProduit(LigneCommande item) {
		if (produits == null) {
			return;
		}
		produits.remove(item);
	}

	/**
	 * Clear the {@code produits} collection.
	 *
	 * <p>
	 * If you have to query {@link LigneCommande} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearProduits() {
		if (produits != null) {
			produits.clear();
		}
	}

	public BigDecimal getCommandeHT() {
		return commandeHT == null ? BigDecimal.ZERO : commandeHT;
	}

	public void setCommandeHT(BigDecimal commandeHT) {
		this.commandeHT = commandeHT;
	}

	public BigDecimal getCommandeTTC() {
		return commandeTTC == null ? BigDecimal.ZERO : commandeTTC;
	}

	public void setCommandeTTC(BigDecimal commandeTTC) {
		this.commandeTTC = commandeTTC;
	}

	public Integer getStatusSelect() {
		return statusSelect == null ? 0 : statusSelect;
	}

	public void setStatusSelect(Integer statusSelect) {
		this.statusSelect = statusSelect;
	}

	public Boolean getFactureGeneree() {
		return factureGeneree == null ? Boolean.FALSE : factureGeneree;
	}

	public void setFactureGeneree(Boolean factureGeneree) {
		this.factureGeneree = factureGeneree;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof Commande)) return false;

		final Commande other = (Commande) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		final MoreObjects.ToStringHelper tsh = MoreObjects.toStringHelper(this);

		tsh.add("id", this.getId());
		tsh.add("dateCommande", this.getDateCommande());
		tsh.add("datePrevFacture", this.getDatePrevFacture());
		tsh.add("commandeHT", this.getCommandeHT());
		tsh.add("commandeTTC", this.getCommandeTTC());
		tsh.add("statusSelect", this.getStatusSelect());
		tsh.add("factureGeneree", this.getFactureGeneree());

		return tsh.omitNullValues().toString();
	}
}
