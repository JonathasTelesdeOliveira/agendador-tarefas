package com.jhonatanTeles.agendadorTarefas.busines.Mapper;

import com.jhonatanTeles.agendadorTarefas.Infraestruture.entity.TarefasEntity;
import com.jhonatanTeles.agendadorTarefas.busines.dto.TarefaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

// ("Caso  TarefasDTO for Nullo estiver vazio preecher como TarefaEntity")
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefas(TarefaDTO dto, @MappingTarget TarefasEntity entity);
}