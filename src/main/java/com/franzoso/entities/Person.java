package com.franzoso.entities;


import java.math.BigDecimal;

public record Person(String name, Integer age, BigDecimal wage, Occupation occupation) {
}
