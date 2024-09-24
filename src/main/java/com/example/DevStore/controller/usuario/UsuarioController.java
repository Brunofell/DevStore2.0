package com.example.DevStore.controller.usuario;

import com.example.DevStore.DTO.usuario.DadosCadastroUsuario;
import com.example.DevStore.DTO.usuario.DadosListagemUsuario;
import com.example.DevStore.DTO.usuario.DadosLoginUsuario;
import com.example.DevStore.DTO.usuario.DadosUpdateUsuario;
import com.example.DevStore.entity.Usuario;
import com.example.DevStore.repository.UsuarioRepository;
import com.example.DevStore.security.usuario.DadosAuthUsuario;
import com.example.DevStore.security.usuario.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @PostMapping("cadastrar")
//    public void cadastrar(@RequestBody DadosCadastroUsuario dados){
//        repository.save(new Usuario(dados));
//        System.out.println(dados);
//    }

    @PostMapping("/cadastrar")
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroUsuario dados){


        if(repository.existsByEmail(dados.email())){
            throw new RuntimeException("Este e-mail já está em uso.");
        }


        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        Usuario usuario = new Usuario(dados);
        usuario.setSenha(senhaCriptografada);

        repository.save(usuario);
    }


    @GetMapping("listar")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public List<DadosListagemUsuario> listar(){
        return repository.findAllByAtivoTrue().stream().map(DadosListagemUsuario::new).toList();
    }

    @DeleteMapping("/{id}")
    @Transactional // exclusão lógica
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public void excluir(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        usuario.excluir();
    }

    @PutMapping("atualizar")
    @Transactional
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public void atualizarPaciente(@RequestBody @Valid DadosUpdateUsuario dados){

        var usuario = repository.getReferenceById(dados.id());

        usuario.atualizarInfos(dados);

        repository.save(usuario);
    }


    @PostMapping("login")
    public ResponseEntity<DadosLoginUsuario> login(@RequestBody @Valid DadosAuthUsuario dadosAuthUsuario){
        var authToken = new UsernamePasswordAuthenticationToken(dadosAuthUsuario.email(), dadosAuthUsuario.senha());
        var authentication = manager.authenticate(authToken);

        var usuario = (Usuario) authentication.getPrincipal();

        if (!usuario.isAtivo()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        var respostaLogin = new DadosLoginUsuario(tokenJWT, "usuario", usuario.getId());

        return ResponseEntity.ok(respostaLogin);
    }


}
