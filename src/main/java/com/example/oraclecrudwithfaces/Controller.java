package com.example.oraclecrudwithfaces;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Controller implements Serializable {
    private List<Hai> data;
    //@ManagedProperty(value="hai")
    private Hai hai;

    public void checkPlain(){
        data=(List<Hai>) new DAO().all();
        hai=data.get(0);
    }

    @PostConstruct
    public void sell(){
        data=(List<Hai>) new DAO().all();
        //setHai(data.get(0));
    }
}
