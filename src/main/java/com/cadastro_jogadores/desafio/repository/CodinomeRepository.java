package com.cadastro_jogadores.desafio.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface CodinomeRepository {
    CodinomeDTO buscarCodinomes() throws JsonMappingException, JsonProcessingException;
}