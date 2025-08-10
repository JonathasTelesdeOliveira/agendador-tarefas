package com.jhonatanTeles.agendadorTarefas.busines.Mapper;

import com.jhonatanTeles.agendadorTarefas.busines.dto.TarefaDTO;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
    TarefasEntity paraTarefasEntity(TarefaDTO tarefaDTO);

    TarefaDTO paraTarefaDTO(TarefasEntity tarefasEntity);
}
