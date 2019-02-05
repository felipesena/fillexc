package com.fillexc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Id
    private int id;

    private String email;
}
