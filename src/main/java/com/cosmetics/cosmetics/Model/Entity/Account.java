package com.cosmetics.cosmetics.Model.Entity;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    private String userName;

    @Column(name = "password")
    @NotEmpty(message = "cannot generate password")
    private String password;

    @Column(name = "status")
    private boolean status;

    @Column(name = "otp")
    private String otp;
    
    @Column(name = "create_time")
    private Timestamp createTime;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role", referencedColumnName = "id")
	private Role role;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_information", referencedColumnName = "id")
    private UserInformation userInformation;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DeliveryInformation> deliveryInformations;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VourcherDetail> vourcherDetails;
    
    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cart cart;
    
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Rating> ratings;
}