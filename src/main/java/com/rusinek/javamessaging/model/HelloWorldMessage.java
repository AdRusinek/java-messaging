package com.rusinek.javamessaging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Adrian Rusinek on 06.04.2020
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldMessage implements Serializable {

    static final long serialVersionUID = 6677528177779199926L;

    private UUID id;
    private String message;
}
