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
@Table(name = "GESTION_FACTURE")
public class Facture extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_FACTURE_SEQ")
	@SequenceGenerator(name = "GESTION_FACTURE_SEQ", sequenceName = "GESTION_FACTURE_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	@Index(name = "GESTION_FACTURE_NOM_IDX")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Client nom;

	@NotNull
	private LocalDate dateFacture;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facture", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LigneFacture> produits;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal factureHT = BigDecimal.ZERO;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal factureTTC = BigDecimal.ZERO;

	@Widget(title = "Statut", readonly = true, selection = "facture.status.select")
	private Integer statusSelect = 1;

	private Boolean ecritureGeneree = Boolean.FALSE;

	public Facture() {
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

	public LocalDate getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(LocalDate dateFacture) {
		this.dateFacture = dateFacture;
	}

	public List<LigneFacture> getProduits() {
		return produits;
	}

	public void setProduits(List<LigneFacture> produits) {
		this.produits = produits;
	}

	/**
	 * Add the given {@link LigneFacture} item to the {@code produits}.
	 *
	 * <p>
	 * It sets {@code item.facture = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addProduit(LigneFacture item) {
		if (produits == null) {
			produits = new ArrayList<LigneFacture>();
		}
		produits.add(item);
		item.setFacture(this);
	}

	/**
	 * Remove the given {@link LigneFacture} item from the {@code produits}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeProduit(LigneFacture item) {
		if (produits == null) {
			return;
		}
		produits.remove(item);
	}

	/**
	 * Clear the {@code produits} collection.
	 *
	 * <p>
	 * If you have to query {@link LigneFacture} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearProduits() {
		if (produits != null) {
			produits.clear();
		}
	}

	public BigDecimal getFactureHT() {
		return factureHT == null ? BigDecimal.ZERO : factureHT;
	}

	public void setFactureHT(BigDecimal factureHT) {
		this.factureHT = factureHT;
	}

	public BigDecimal getFactureTTC() {
		return factureTTC == null ? BigDecimal.ZERO : factureTTC;
	}

	public void setFactureTTC(BigDecimal factureTTC) {
		this.factureTTC = factureTTC;
	}

	public Integer getStatusSelect() {
		return statusSelect == null ? 0 : statusSelect;
	}

	public void setStatusSelect(Integer statusSelect) {
		this.statusSelect = statusSelect;
	}

	public Boolean getEcritureGeneree() {
		return ecritureGeneree == null ? Boolean.FALSE : ecritureGeneree;
	}

	public void setEcritureGeneree(Boolean ecritureGeneree) {
		this.ecritureGeneree = ecritureGeneree;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof Facture)) return false;

		final Facture other = (Facture) obj;
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
		tsh.add("dateFacture", this.getDateFacture());
		tsh.add("factureHT", this.getFactureHT());
		tsh.add("factureTTC", this.getFactureTTC());
		tsh.add("statusSelect", this.getStatusSelect());
		tsh.add("ecritureGeneree", this.getEcritureGeneree());

		return tsh.omitNullValues().toString();
	}
}
