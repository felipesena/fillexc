package com.fillexc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Email {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Id
    private int id;

    private String email;
}
