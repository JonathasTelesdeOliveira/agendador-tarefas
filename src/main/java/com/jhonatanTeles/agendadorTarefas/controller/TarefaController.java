package com.jhonatanTeles.agendadorTarefas.controller;

import com.jhonatanTeles.agendadorTarefas.busines.TarefaService;
import com.jhonatanTeles.agendadorTarefas.busines.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefa(@RequestBody TarefaDTO dto,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(
                tarefaService.gravandoTarefaDTO(token, dto));
    }
}
