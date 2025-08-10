package com.jhonatanTeles.agendadorTarefas.Infraestruture.repository;

import com.jhonatanTeles.agendadorTarefas.Infraestruture.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends MongoRepository<TarefasEntity, String> {

}
