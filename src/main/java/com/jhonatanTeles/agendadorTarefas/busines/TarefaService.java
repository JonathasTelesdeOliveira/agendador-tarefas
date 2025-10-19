package com.jhonatanTeles.agendadorTarefas.busines;

import com.jhonatanTeles.agendadorTarefas.Infraestruture.Enums.StatusNotificacaoEnum;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.entity.TarefasEntity;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.exceptions.ResourceNotFoundException;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.repository.TarefaRepository;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.security.JwtUtil;
import com.jhonatanTeles.agendadorTarefas.busines.Mapper.TarefaMapper;
import com.jhonatanTeles.agendadorTarefas.busines.converter.TarefaConverter;
import com.jhonatanTeles.agendadorTarefas.busines.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<TarefaDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                            LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefaDTO_Build(
                tarefaRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal,
                        StatusNotificacaoEnum.Pendente));
    }

    public List<TarefaDTO> buscarTarefaPorEmail(String token) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        System.out.println("Token recebido: " + token);
        System.out.println("Email extraído: " + email);
        List<TarefasEntity> listaTarefas = tarefaRepository.findByEmailUsuario(email);
        System.out.println("Tarefas encontradas: " + listaTarefas.size());
        List<TarefaDTO> dtos = tarefaConverter.paraListaTarefaDTO_Build(listaTarefas);
        System.out.println("DTOs mapeados: " + dtos);
        return dtos;

    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id não existe! " + id,
                    e.getCause());
        }
    }

    public TarefaDTO alteraStatusTarefa(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefaRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrado!" + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaMapper.paraTarefaDTO(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao cadastrar tarefa! " + e.getCause());
        }
    }

    public TarefaDTO updateDeTarefas(TarefaDTO dto, String id) {
        try {
            TarefasEntity entity = tarefaRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada! " + id));
            TarefasEntity tarefasEntity = tarefaConverter.updtadeBuiderTarefaEntity(dto, entity);
            return tarefaConverter.paraTarefaDTO_builder(tarefaRepository.save(tarefasEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao cadastrar tarefa!" + e.getCause());
        }
    }
}
