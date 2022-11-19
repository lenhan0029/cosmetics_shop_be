package com.cosmetics.cosmetics.Model.Entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {

	@EmbeddedId
	RatingKey id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idProduct")
	@JoinColumn(name = "id_product")
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idAccount")
	@JoinColumn(name = "id_account")
	private Account account;
	
	@Column(name = "start")
	private int star;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "Date")
	private Date date;
}
