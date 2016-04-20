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
@Table(name = "GESTION_LIGNE_COMMANDE")
public class LigneCommande extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_LIGNE_COMMANDE_SEQ")
	@SequenceGenerator(name = "GESTION_LIGNE_COMMANDE_SEQ", sequenceName = "GESTION_LIGNE_COMMANDE_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	@Index(name = "GESTION_LIGNE_COMMANDE_COMMANDE_IDX")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Commande commande;

	@NotNull
	@Index(name = "GESTION_LIGNE_COMMANDE_PRODUIT_IDX")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Produit produit;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal price = BigDecimal.ZERO;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal priceHT = BigDecimal.ZERO;

	@NotNull
	@Min(1)
	private Integer quantity = 0;

	@DecimalMin("0.0")
	@Digits(integer = 2, fraction = 4)
	private BigDecimal taux = BigDecimal.ZERO;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal priceTTC = BigDecimal.ZERO;

	public LigneCommande() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public BigDecimal getPrice() {
		return price == null ? BigDecimal.ZERO : price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPriceHT() {
		return priceHT == null ? BigDecimal.ZERO : priceHT;
	}

	public void setPriceHT(BigDecimal priceHT) {
		this.priceHT = priceHT;
	}

	public Integer getQuantity() {
		return quantity == null ? 0 : quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTaux() {
		return taux == null ? BigDecimal.ZERO : taux;
	}

	public void setTaux(BigDecimal taux) {
		this.taux = taux;
	}

	public BigDecimal getPriceTTC() {
		return priceTTC == null ? BigDecimal.ZERO : priceTTC;
	}

	public void setPriceTTC(BigDecimal priceTTC) {
		this.priceTTC = priceTTC;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof LigneCommande)) return false;

		final LigneCommande other = (LigneCommande) obj;
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
		tsh.add("price", this.getPrice());
		tsh.add("priceHT", this.getPriceHT());
		tsh.add("quantity", this.getQuantity());
		tsh.add("taux", this.getTaux());
		tsh.add("priceTTC", this.getPriceTTC());

		return tsh.omitNullValues().toString();
	}
}
