package com.jhonatanTeles.agendadorTarefas.busines.converter;

import com.jhonatanTeles.agendadorTarefas.Infraestruture.entity.TarefasEntity;
import com.jhonatanTeles.agendadorTarefas.busines.dto.TarefaDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TarefaConverter {

    public List<TarefaDTO> paraListaTarefaDTO_Build(List<TarefasEntity> listaTarefaDTOS) {
        return listaTarefaDTOS.stream().map(this::paraTarefaDTO_builder).toList();
    }

    public TarefaDTO paraTarefaDTO_builder(TarefasEntity entity){
        return TarefaDTO.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .dataCriacao(entity.getDataCriacao())
                .dataEvento(entity.getDataEvento())
                .emailUsuario(entity.getEmailUsuario())
                .dataAlteracao(entity.getDataAlteracao())
                .statusNotificacaoEnum(entity.getStatusNotificacaoEnum())
                .build();
    }
    public TarefasEntity updtadeBuiderTarefaEntity(TarefaDTO dto, TarefasEntity entity){
        return TarefasEntity.builder()
                .id(entity.getId())
                .descricao(dto.getDescricao() != null ? dto.getDescricao() : entity.getDescricao())
                .dataCriacao(dto.getDataCriacao() != null ? dto.getDataCriacao() : entity.getDataCriacao())
                .dataEvento(dto.getDataEvento() != null ? dto.getDataEvento() : entity.getDataEvento())
                .emailUsuario(dto.getEmailUsuario() != null ? dto.getEmailUsuario() : entity.getEmailUsuario())
                .dataAlteracao(dto.getDataAlteracao() != null ? dto.getDataAlteracao() : entity.getDataAlteracao())
                .statusNotificacaoEnum(dto.getStatusNotificacaoEnum() != null ? dto.getStatusNotificacaoEnum() : entity.getStatusNotificacaoEnum())
                .build();
    }
}
