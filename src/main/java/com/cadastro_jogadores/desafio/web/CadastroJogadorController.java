package com.cadastro_jogadores.desafio.web;

import com.cadastro_jogadores.desafio.exception.GrupoCodinomeIndisponivelException;
import com.cadastro_jogadores.desafio.model.GrupoCodinome;
import com.cadastro_jogadores.desafio.model.Jogador;
import com.cadastro_jogadores.desafio.serivce.JogadorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;


@Controller
@RequestMapping("cadastro-jogador")
public class CadastroJogadorController {
    private final JogadorService jogadorService;

    public CadastroJogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String paginaCadastroJogador(Model model) {
        return getViewAndModel(model, new Jogador(null, null, null, null, null));
    }

    /*
     * BindingResult: objeto que contém os resultados da validação dos dados do
     * formulário.
     * Model: passar dados do controlador para a view.
     */
    @PostMapping
    public String cadastrarJogador(@ModelAttribute @Valid Jogador jogador,
                                   BindingResult result, Model model)
            throws JsonProcessingException {
        if (result.hasErrors())
            return getViewAndModel(model, jogador);

        try {
            jogadorService.registrarJogador(jogador);
            return "redirect:/listagem-jogadores";
        } catch (GrupoCodinomeIndisponivelException e) {
            result.rejectValue("grupoCodinome", "grupoCodinome", e.getMessage());
            return getViewAndModel(model, jogador);
        }
    }

    private String getViewAndModel(Model model, Jogador jogador) {
        model.addAttribute("jogador", jogador);
        model.addAttribute("gruposCodinomes", GrupoCodinome.values());
        return "cadastro_jogador";
    }
}