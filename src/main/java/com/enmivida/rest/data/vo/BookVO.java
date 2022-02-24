package com.enmivida.rest.data.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookVO extends RepresentationModel<BookVO> implements Serializable {

    private static final long serialVersionUID = -4232484114614753254L;

    private Long id;
    private String author;
    private Date launchDate;
    private Double price;
    private String title;
}
