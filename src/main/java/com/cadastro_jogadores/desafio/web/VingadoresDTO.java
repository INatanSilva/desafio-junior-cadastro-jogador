package com.cadastro_jogadores.desafio.web;

import java.util.List;
import java.util.stream.Collectors;

public record VingadoresDTO(List<Codinome> vingadores) implements CodinomeDTO {

    @Override
    public List<String> getCodinomes() {
        return vingadores.stream().map(Codinome::codinome).collect(Collectors.toList());
    }
}

record Codinome(String codinome) {

}