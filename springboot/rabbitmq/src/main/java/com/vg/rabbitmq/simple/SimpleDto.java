package com.vg.rabbitmq.simple;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SimpleDto implements Serializable {

    private Integer id;

    private String name;
}
