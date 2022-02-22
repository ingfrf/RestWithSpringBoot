package com.enmivida.rest.yml.converter;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    // se cambió de protected a public
    public YamlJackson2HttpMessageConverter() {
        super(new YAMLMapper(), MediaType.parseMediaType("application/x-yaml"));
    }
}
