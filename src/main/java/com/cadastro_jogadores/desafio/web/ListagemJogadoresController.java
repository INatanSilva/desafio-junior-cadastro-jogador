package com.cadastro_jogadores.desafio.web;

import com.cadastro_jogadores.desafio.serivce.JogadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("listagem-jogadores")
public class ListagemJogadoresController {
    private final JogadorService jogadorService;

    public ListagemJogadoresController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String listarJogadores(Model model) {
        var jogadores = jogadorService.listarJogadores();
        model.addAttribute("jogadores", jogadores);
        return "listagem_jogadores";
    }
}