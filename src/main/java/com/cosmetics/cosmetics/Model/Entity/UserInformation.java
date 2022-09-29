package com.cosmetics.cosmetics.Model.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "user_information")
public class UserInformation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "first_name")
    @NotEmpty(message = "cannot generate firstname")
    private String firstName;
	
	@Column(name = "last_name")
    @NotEmpty(message = "cannot generate lastname")
    private String lastName;
	
	@Column(name = "address")
    private String address;
	
	@Column(name = "phone_number")
	@NotEmpty(message = "cannot generate phone number")
    private String phoneNumber;
	
	@Column(name = "email")
    private String email;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_account", referencedColumnName = "id")
	private Account account;
}
