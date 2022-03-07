package com.enmivida.rest.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccountCredentialsVO implements Serializable {

    private static final long serialVersionUID = -6414550693792843207L;

    private String username;
    private String password;
}
