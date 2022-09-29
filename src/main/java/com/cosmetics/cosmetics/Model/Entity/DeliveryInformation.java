package com.cosmetics.cosmetics.Model.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "delivery_information")
public class DeliveryInformation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "name")
    @NotEmpty(message = "cannot generate name")
    private String name;
	
	@Column(name = "address")
	@NotEmpty(message = "cannot generate address")
    private String address;
	
	@Column(name = "phone_number")
	@NotEmpty(message = "cannot generate phone number")
    private String phoneNumber;
	
	@Column(name = "is_default")
    private boolean isDefault;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_account")
	private Account account;
	
	@OneToOne(mappedBy = "deliveryInformation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Order order;
}
