package com.enmivida.rest.data.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PersonVO implements Serializable {
    private static final long serialVersionUID = -765522284080245815L;

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
}
