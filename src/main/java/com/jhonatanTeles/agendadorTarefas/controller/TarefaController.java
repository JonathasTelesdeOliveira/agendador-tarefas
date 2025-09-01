package com.jhonatanTeles.agendadorTarefas.controller;

import com.jhonatanTeles.agendadorTarefas.Infraestruture.Enums.StatusNotificacaoEnum;
import com.jhonatanTeles.agendadorTarefas.busines.TarefaService;
import com.jhonatanTeles.agendadorTarefas.busines.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefa(@RequestBody TarefaDTO dto,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(
                tarefaService.gravandoTarefaDTO(token, dto));
    }

    @GetMapping("/evento")
    public ResponseEntity<List<TarefaDTO>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInical,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscarTarefasAgendadasPorPeriodo(dataInical, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscarTarefaEmail(
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscarTarefaPorEmail(token));
        /*
        List<TarefaDTO> tarefaDTOS = tarefaService.buscarTarefaPorEmail(token);
        return ResponseEntity.ok(tarefaDTOS);
        */
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaId(
            @RequestParam("id") String id) {
        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTO> atualizarStatusTarefa(@RequestParam("status") StatusNotificacaoEnum status,
                                                            @RequestParam("id") String id) {
            return ResponseEntity.ok(tarefaService.alteraStatusTarefa(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> updateTarefa(@RequestBody TarefaDTO dto,
                                                  @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.updateDeTarefas(dto, id));
    }
}
