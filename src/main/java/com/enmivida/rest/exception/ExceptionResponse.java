package com.enmivida.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 3012601463785524285L;

    private Long timestamp;
    private String message;
    private String details;
}
