package com.jhonatanTeles.agendadorTarefas.busines;

import com.jhonatanTeles.agendadorTarefas.Infraestruture.Enums.StatusNotificacaoEnum;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.security.JwtUtil;
import com.jhonatanTeles.agendadorTarefas.busines.Mapper.TarefaMapper;
import com.jhonatanTeles.agendadorTarefas.busines.dto.TarefaDTO;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.entity.TarefasEntity;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final JwtUtil jwtUtil;

    public TarefaDTO gravandoTarefaDTO(String token, TarefaDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.Pendente);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaMapper.paraTarefasEntity(dto);
        return tarefaMapper.paraTarefaDTO(
                tarefaRepository.save(entity));
    }

}
