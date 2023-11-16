package services;

import domain.Usuario;
import services.repositories.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario){
        return repository.salvar(usuario);
    }
}
