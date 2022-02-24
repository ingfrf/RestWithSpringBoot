package com.enmivida.rest.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "books")
public class Book implements Serializable {

    private static final long serialVersionUID = -831766957876704599L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String author;
    @Column(name = "launch_date")
    private Date launchDate;
    @Column
    private Double price;
    @Column
    private String title;
}
