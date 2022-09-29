package com.cosmetics.cosmetics.Model.Entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "vourcher")
public class Vourcher {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "name")
    @NotEmpty(message = "cannot generate name")
    private String name;
	
	@Column(name = "code")
    @NotEmpty(message = "cannot generate code")
    private String code;
	
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
	
	@Column(name = "quantity")
    @NotEmpty(message = "cannot generate quantity")
	private Integer quantity;
	
	@Column(name = "status")
    @NotEmpty(message = "cannot generate status")
	private boolean status;
	
	@Column(name = "condition")
    @NotEmpty(message = "cannot generate condition")
	private Integer condition;
	
	@OneToOne(mappedBy = "vourcher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Order order;
	
	@OneToMany(mappedBy = "vourcher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VourcherDetail> vourcherDetails;
}
