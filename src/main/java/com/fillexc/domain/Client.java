package com.fillexc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    @Id
    private int id;

    @ApiModelProperty(notes = "Name of the client")
    private String name;

    @ApiModelProperty(notes = "Social name that is going to use to charge this client")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "social_name_id", referencedColumnName = "social_name_id")
    private SocialName provider;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "client_id", nullable = false)
    private List<Email> emails = new ArrayList();
}
