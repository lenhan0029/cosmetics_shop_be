package com.cosmetics.cosmetics.Model.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "product")
public class Product {

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
	
	@Column(name = "price")
    @NotEmpty(message = "cannot generate price")
    private Integer price;
	
	@Column(name = "quantity_usable")
    @NotEmpty(message = "cannot generate quantity usable")
    private Integer quantityUsable;
	
	@Column(name = "quantity_unusable")
    @NotEmpty(message = "cannot generate quantity unusable")
    private Integer quantityUnusable;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_brand")
	private Brand brand;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_type")
	private Type type;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_promotion", referencedColumnName = "id")
	private Promotion promotion;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CartDetail> cartDetails;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OrderDetail> orderDetails;
}
