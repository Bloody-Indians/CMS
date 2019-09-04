package com.bawag.cms.auth.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "authority")
public class Authority {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "authority_id")
    private int id;
    @Column(name = "authority_name")
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    public Authority() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }
}