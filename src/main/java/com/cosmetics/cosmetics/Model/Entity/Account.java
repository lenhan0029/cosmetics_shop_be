package com.cosmetics.cosmetics.Model.Entity;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    @NotEmpty(message = "cannot generate username")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "cannot generate password")
    private String password;

    @Column(name = "status")
    private boolean status;

    @Column(name = "otp")
    private String otp;
    
    @Column(name = "create_time")
    private Timestamp createTime;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    @JsonIgnore
	private Role role;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_information", referencedColumnName = "id")
    @JsonIgnore
    private UserInformation userInformation;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<DeliveryInformation> deliveryInformations;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Order> orders;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<VourcherDetail> vourcherDetails;
    
    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private Cart cart;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Rating> ratings;
    
    @OneToMany(mappedBy = "shipper", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Order> shipperorders;
}