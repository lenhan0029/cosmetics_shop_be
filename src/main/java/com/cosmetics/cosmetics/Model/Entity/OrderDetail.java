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
@Table(name = "order_detail")
public class OrderDetail {

	@EmbeddedId
	OrderKey id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idOrder")
	@JoinColumn(name = "id_order")
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idProduct")
	@JoinColumn(name = "id_product")
	private Product product;
	
	@Column(name = "quantity")
    @NotEmpty(message = "cannot generate quantity")
    private Integer quantity;
	
	@Column(name = "price")
    @NotEmpty(message = "cannot generate price")
    private Float price;
	
	
}
