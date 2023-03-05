package com.example.oraclecrudwithfaces;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Named
@SessionScoped
public class Hai implements Serializable {
    private int id;
    private String name;
    private int price;
}
