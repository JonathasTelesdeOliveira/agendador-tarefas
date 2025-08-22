package com.jhonatanTeles.agendadorTarefas.busines.Mapper;

import com.jhonatanTeles.agendadorTarefas.busines.dto.TarefaDTO;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    TarefasEntity paraTarefasEntity(TarefaDTO tarefaDTO);

    TarefaDTO paraTarefaDTO(TarefasEntity tarefasEntity);

    List<TarefasEntity> paraListaTarefaEntity(List<TarefaDTO> dto);

    List<TarefaDTO> paraListaTarefaDTO(List<TarefasEntity> Entity);
}

