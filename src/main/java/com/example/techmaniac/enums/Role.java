package com.example.techmaniac.enums;

import javax.management.InstanceNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ADMIN(0, "ADMIN"),
    USER(1, "USER");

    private Integer code;
    private String name;

    public static Role getByCode(Integer code) throws InstanceNotFoundException {
        for (Role role : Role.values()) {
            if (role.code == code) {
                return role;
            }
        }
        throw new InstanceNotFoundException(
                "Enum Role with value " + code + " does not exist");
    }

    public static Role getByDescription(String name)
            throws InstanceNotFoundException {
        for (Role role : Role.values()) {
            if (role.getName().equals(name)) {
                return role;
            }
        }
        throw new InstanceNotFoundException(
                "Enum Role with value " + name + " does not exist");
    }
}
