package com.cadastro_jogadores.desafio.serivce;

import java.util.List;

import com.cadastro_jogadores.desafio.exception.GrupoCodinomeIndisponivelException;
import com.cadastro_jogadores.desafio.model.GrupoCodinome;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class CodinomeService {
    private final CodinomeRepositoryFactory codinomeRepositoryFactory;

    public CodinomeService(CodinomeRepositoryFactory codinomeRepositoryFactory) {
        this.codinomeRepositoryFactory = codinomeRepositoryFactory;
    }

    public String gerarCodinome(GrupoCodinome grupoCodinome, List<String> codinomesEmUso)
            throws JsonProcessingException {
        var codinomesDisponiveis = listCodinomesDisponiveis(grupoCodinome, codinomesEmUso);
        if (codinomesDisponiveis.isEmpty())
            throw new GrupoCodinomeIndisponivelException();

        var codinomeSorteado = sortearCodinome(codinomesDisponiveis);
        return codinomeSorteado;
    }

    private List<String> listCodinomesDisponiveis(GrupoCodinome grupoCodinome, List<String> codinomesEmUso)
            throws JsonProcessingException {
        var codinomes = buscarCodinomes(grupoCodinome);

        var codinomesDisponiveis = codinomes
                .stream()
                .filter(codinome -> !codinomesEmUso.contains(codinome))
                .toList();

        return codinomesDisponiveis;
    }

    private List<String> buscarCodinomes(GrupoCodinome grupoCodinome)
            throws JsonProcessingException {
        var codinomeRepository = codinomeRepositoryFactory.create(grupoCodinome);
        return codinomeRepository.buscarCodinomes().getCodinomes();
    }

    private String sortearCodinome(List<String> codinomesDisponiveis) {
        return codinomesDisponiveis
                .get((int) (Math.random() * codinomesDisponiveis.size()));
    }

}