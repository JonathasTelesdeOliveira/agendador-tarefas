package com.jhonatanTeles.agendadorTarefas.Infraestruture.security;

import com.jhonatanTeles.agendadorTarefas.busines.dto.UsuarioDTO;
import com.jhonatanTeles.agendadorTarefas.Infraestruture.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient client;

    // Implementação do método para carregar detalhes do usuário pelo e-mail

    public UserDetails carregaDadosDoUsuario(String email, String token){
        UsuarioDTO usuarioDTO = client.buscarPorEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }

}
