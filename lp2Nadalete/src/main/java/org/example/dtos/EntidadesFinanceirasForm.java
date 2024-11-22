package org.example.dtos;

import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.core.annotation.Introspected;

@Serdeable
@Introspected
public class EntidadesFinanceirasForm {

    private String entfinNome;


    public String getEntfinNome() {
        return entfinNome;
    }

    public void setEntfinNome(String entfinNome) {
        this.entfinNome = entfinNome;
    }

}
