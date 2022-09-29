package com.cosmetics.cosmetics.Model.Entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotion")
public class Promotion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "name")
    @NotEmpty(message = "cannot generate name")
    private String name;
	
	@Column(name = "image")
    @NotEmpty(message = "cannot generate image")
    private String image;
	
	@Column(name = "started_date")
    @NotEmpty(message = "cannot generate started date")
    private Date startedDate;
	
	@Column(name = "expired_date")
    @NotEmpty(message = "cannot generate expired date")
    private Date expiredDate;
	
	@Column(name = "percentage")
    @NotEmpty(message = "cannot generate percentage")
    private Integer percentage;
	
	@OneToOne(mappedBy = "promotion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Product product;
}
