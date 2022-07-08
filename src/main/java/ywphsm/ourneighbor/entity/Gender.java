package ywphsm.ourneighbor.entity;

import lombok.Getter;

@Getter
public enum Gender {
    MAN("남"), WOMEN("여");

    private final String description;

    Gender(String description) {
        this.description = description;
    }
}
