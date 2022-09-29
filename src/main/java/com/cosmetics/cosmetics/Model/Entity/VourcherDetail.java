package com.cosmetics.cosmetics.Model.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Table(name = "vourcher_detail")
public class VourcherDetail {

	@EmbeddedId
	VourcherKey id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId("idAccount")
	@JoinColumn(name = "id_account")
	private Account account;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId("idVourcher")
	@JoinColumn(name = "id_vourcher")
	private Vourcher vourcher;
	
	@Column(name = "usages")
    @NotEmpty(message = "cannot generate usages")
	private Integer usages;
}
