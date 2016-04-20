package com.axelor.gestion.db;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.HashKey;
import com.axelor.db.annotations.NameColumn;
import com.google.common.base.MoreObjects;

/**
 * This is a simple Hello object.
 */
@Entity
@Cacheable
@Table(name = "GESTION_HELLO_GESTION")
public class HelloGestion extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_HELLO_GESTION_SEQ")
	@SequenceGenerator(name = "GESTION_HELLO_GESTION_SEQ", sequenceName = "GESTION_HELLO_GESTION_SEQ", allocationSize = 1)
	private Long id;

	@HashKey
	@NameColumn
	@NotNull
	@Column(unique = true)
	private String title;

	private LocalDateTime publishedOn;

	private String tags;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.hibernate.type.TextType")
	private String message;

	public HelloGestion() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getPublishedOn() {
		return publishedOn;
	}

	public void setPublishedOn(LocalDateTime publishedOn) {
		this.publishedOn = publishedOn;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof HelloGestion)) return false;

		final HelloGestion other = (HelloGestion) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		if (!Objects.equals(getTitle(), other.getTitle())) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(-311635241, this.getTitle());
	}

	@Override
	public String toString() {
		final MoreObjects.ToStringHelper tsh = MoreObjects.toStringHelper(this);

		tsh.add("id", this.getId());
		tsh.add("title", this.getTitle());
		tsh.add("publishedOn", this.getPublishedOn());
		tsh.add("tags", this.getTags());

		return tsh.omitNullValues().toString();
	}
}
