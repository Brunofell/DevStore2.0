package com.example.DevStore.security;


import com.example.DevStore.entity.Usuario;
import com.example.DevStore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // se tiver dando erro é culpa disso aqui
public class AuthenticateService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tenta encontrar o usuário como médico
        Usuario usuario = (Usuario) repository.findByEmail(username);
        if (usuario != null) {
            return usuario;
        }

        // Se nenhum usuário for encontrado, lança uma exceção
        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }
}
