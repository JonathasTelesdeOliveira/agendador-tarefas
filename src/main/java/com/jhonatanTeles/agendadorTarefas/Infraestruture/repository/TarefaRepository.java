package com.jhonatanTeles.agendadorTarefas.Infraestruture.repository;

import com.jhonatanTeles.agendadorTarefas.Infraestruture.Enums.StatusNotificacaoEnum;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<TarefasEntity, String> {
    List<TarefasEntity> findByDataEventoBetweenAndStatusNotificacaoEnum(LocalDateTime dataInicial,
                                                LocalDateTime dataFinal,
                                                StatusNotificacaoEnum status);

    List<TarefasEntity> findByEmailUsuario(String email);
}
