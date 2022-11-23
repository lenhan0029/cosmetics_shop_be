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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "created_date")
    private Date createdDate;
	
	@Column(name = "updated_date")
    private Date updatedDate;
	
	@Column(name = "paid_status")
    private boolean paidStatus;
	
	@Column(name = "total")
    private Float total;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<OrderDetail> orderDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_status")
	@JsonIgnore
	private Status status;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_vourcher", referencedColumnName = "id")
	@JsonIgnore
	private Vourcher vourcher;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_delivery_information")
	@JsonIgnore
	private DeliveryInformation deliveryInformation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_account")
	@JsonIgnore
	private Account account;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_shipper")
//	@JsonIgnore
//	private Account shipper;
}
