package com.fillexc.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Client {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "provider", referencedColumnName = "id")
    private SocialName provider;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "client")
    private List<Email> emails = new ArrayList();
}
