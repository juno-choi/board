package com.juno.simple.global.api;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResponseEnums {
    SUCCESS("0000", "success"),
    ;

    public final String code;
    public final String message;
}
