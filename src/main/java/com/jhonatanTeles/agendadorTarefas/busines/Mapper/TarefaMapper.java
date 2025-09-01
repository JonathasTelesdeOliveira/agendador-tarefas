package com.jhonatanTeles.agendadorTarefas.busines.Mapper;

import com.jhonatanTeles.agendadorTarefas.Infraestruture.entity.TarefasEntity;
import com.jhonatanTeles.agendadorTarefas.busines.dto.TarefaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
/*    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
*/  TarefasEntity paraTarefasEntity(TarefaDTO tarefaDTO);

    TarefaDTO paraTarefaDTO(TarefasEntity tarefasEntity);

    List<TarefaDTO> paraListaTarefaDTO(List<TarefasEntity> Entity);
}

