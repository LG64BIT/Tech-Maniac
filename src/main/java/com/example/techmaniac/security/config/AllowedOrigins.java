package com.example.techmaniac.security.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AllowedOrigins {

    public static final String LOCALHOST_ANY_POR = "http://localhost:[*]";

    public List<String> toList() {
        List<String> allowedOriginsList = new ArrayList<>();
        for (Field attribute : this.getClass().getDeclaredFields()) {
            try {
                allowedOriginsList.add(attribute.get(null).toString());
            }
            catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return allowedOriginsList;
    }
}
