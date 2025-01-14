package com.cadastro_jogadores.desafio.serivce;

import com.cadastro_jogadores.desafio.model.GrupoCodinome;
import com.cadastro_jogadores.desafio.repository.CodinomeRepository;
import com.cadastro_jogadores.desafio.repository.LigaDaJusticaRepository;
import com.cadastro_jogadores.desafio.repository.VingadoresRepository;
import org.springframework.stereotype.Component;

@Component
public class CodinomeRepositoryFactory {

    private final LigaDaJusticaRepository ligaDaJusticaRepository;
    private final VingadoresRepository vingadoresRepository;

    public CodinomeRepositoryFactory(LigaDaJusticaRepository ligaDaJusticaRepository,
                                     VingadoresRepository vingadoresRepository) {
        this.ligaDaJusticaRepository = ligaDaJusticaRepository;
        this.vingadoresRepository = vingadoresRepository;
    }

    public CodinomeRepository create(GrupoCodinome grupoCodinome) {
        return switch (grupoCodinome) {
            case LIGA_DA_JUSTICA -> ligaDaJusticaRepository;
            case VINGADORES -> vingadoresRepository;
        };
    }
}