package com.axelor.gestion.db;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import com.axelor.auth.db.AuditableModel;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "GESTION_LIGNE_FACTURE")
public class LigneFacture extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_LIGNE_FACTURE_SEQ")
	@SequenceGenerator(name = "GESTION_LIGNE_FACTURE_SEQ", sequenceName = "GESTION_LIGNE_FACTURE_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	@Index(name = "GESTION_LIGNE_FACTURE_FACTURE_IDX")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Facture facture;

	@NotNull
	@Index(name = "GESTION_LIGNE_FACTURE_PRODUIT_IDX")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Produit produit;

	@NotNull
	private String compte;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal price = BigDecimal.ZERO;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal prixHT = BigDecimal.ZERO;

	@NotNull
	@Min(1)
	private Integer quantite = 0;

	@DecimalMin("0.0")
	@Digits(integer = 2, fraction = 4)
	private BigDecimal taux = BigDecimal.ZERO;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal prixTTC = BigDecimal.ZERO;

	public LigneFacture() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public String getCompte() {
		return compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	public BigDecimal getPrice() {
		return price == null ? BigDecimal.ZERO : price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrixHT() {
		return prixHT == null ? BigDecimal.ZERO : prixHT;
	}

	public void setPrixHT(BigDecimal prixHT) {
		this.prixHT = prixHT;
	}

	public Integer getQuantite() {
		return quantite == null ? 0 : quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getTaux() {
		return taux == null ? BigDecimal.ZERO : taux;
	}

	public void setTaux(BigDecimal taux) {
		this.taux = taux;
	}

	public BigDecimal getPrixTTC() {
		return prixTTC == null ? BigDecimal.ZERO : prixTTC;
	}

	public void setPrixTTC(BigDecimal prixTTC) {
		this.prixTTC = prixTTC;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof LigneFacture)) return false;

		final LigneFacture other = (LigneFacture) obj;
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
		tsh.add("compte", this.getCompte());
		tsh.add("price", this.getPrice());
		tsh.add("prixHT", this.getPrixHT());
		tsh.add("quantite", this.getQuantite());
		tsh.add("taux", this.getTaux());
		tsh.add("prixTTC", this.getPrixTTC());

		return tsh.omitNullValues().toString();
	}
}
