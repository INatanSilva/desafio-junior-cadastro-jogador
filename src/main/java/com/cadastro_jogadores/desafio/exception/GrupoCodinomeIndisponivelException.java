package com.cadastro_jogadores.desafio.exception;

public class GrupoCodinomeIndisponivelException extends IllegalArgumentException {
    public GrupoCodinomeIndisponivelException() {
        super("Não há codinomes disponíveis para o grupo selecionado.");
    }

}