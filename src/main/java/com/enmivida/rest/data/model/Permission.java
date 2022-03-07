package com.enmivida.rest.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = -7793944793748114966L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String description;

    @Override
    public String getAuthority() {
        return this.description;
    }
}
