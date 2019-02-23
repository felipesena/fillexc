package com.fillexc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SocialName {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_name_id")
    @Id
    private int id;

    private String name;
}
