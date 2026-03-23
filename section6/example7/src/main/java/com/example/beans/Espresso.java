package com.example.beans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("espresso")
public class Espresso implements Coffee {
    @Override
    public String makeCoffee() {
        return "Espresso Coffee";
    }
}
