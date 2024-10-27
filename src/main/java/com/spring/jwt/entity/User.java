package com.spring.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id", nullable = false)

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", initialValue = 1000)
    private Integer id;

    private String name;

    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @Column(name = "mobile_no")
    private String mobileNo;
    @JsonIgnore
    @Column(name = "password", nullable = false, length = 250)
    private String password;

    private String referenceId;

    private Float totalBalnce;

    @JsonIgnore
    private Float todayReferralBalance;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles;

    private String referralId;

    private List<String> referralIdList = new LinkedList<>();

}




