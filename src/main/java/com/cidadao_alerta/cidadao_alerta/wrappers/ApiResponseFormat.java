package com.cidadao_alerta.cidadao_alerta.wrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponseFormat<T> {
    private int status;
    private String message;
    private T data;
}
