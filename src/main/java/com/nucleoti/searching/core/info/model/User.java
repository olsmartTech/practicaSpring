package com.nucleoti.searching.core.info.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Embeddable
@Table(name = "user")
@Data
@NoArgsConstructor // Consutructor sin argumento
public class User implements Serializable {

    private static final long serialVersionUID = 6589898843278314546L;

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

   /* @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name"))
    private List<Role> roles;*/
}
