package com.jhonatanTeles.agendadorTarefas.busines;

import com.jhonatanTeles.agendadorTarefas.Infraestruture.Enums.StatusNotificacaoEnum;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.entity.TarefasEntity;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.repository.TarefaRepository;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.security.JwtUtil;
import com.jhonatanTeles.agendadorTarefas.busines.Mapper.TarefaMapper;
import com.jhonatanTeles.agendadorTarefas.busines.converter.TarefaConverter;
import com.jhonatanTeles.agendadorTarefas.busines.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final JwtUtil jwtUtil;
    private final TarefaConverter tarefaConverter;

    public TarefaDTO gravandoTarefaDTO(String token, TarefaDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.Pendente);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaMapper.paraTarefasEntity(dto);
        return tarefaMapper.paraTarefaDTO(
                tarefaRepository.save(entity));
    }

    public List<TarefaDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaMapper.paraListaTarefaDTO(
                tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefaDTO> buscarTarefaPorEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        return tarefaMapper.paraListaTarefaDTO(
                tarefaRepository.findByEmailUsuario(email));
        /*.orElseTrow(() ->
                    new RuntimeException("Email não cadastrado")); */
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefaRepository.deleteById(id);
        } catch (ResolutionException e) {
            throw new RuntimeException("Erro ao deletar tarefa por id, id não existe! " + id,
                    e.getCause());
        }
    }

    public TarefaDTO alteraStatusTarefa(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefaRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Tarefa não encontrado!" + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaMapper.paraTarefaDTO(tarefaRepository.save(entity));
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao cadastrar tarefa! " + e.getCause());
        }
    }

    public TarefaDTO updateDeTarefas(TarefaDTO dto, String id) {
        try {
            TarefasEntity entity = tarefaRepository.findById(id).
                    orElseThrow(() -> new RuntimeException("Tarefa não encontrada! " + id));
            TarefasEntity tarefasEntity = tarefaConverter.updtadeBuiderTarefaEntity(dto, entity);
            return tarefaConverter.paraTarefaDTO_builder(tarefaRepository.save(tarefasEntity));
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao cadastrar tarefa!" + e.getCause());
        }
    }
}
