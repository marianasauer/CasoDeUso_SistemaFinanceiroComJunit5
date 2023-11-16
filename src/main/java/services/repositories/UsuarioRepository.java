package services.repositories;

import domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Usuario salvar(Usuario usuario);

    //Pode ser que tenha ou não um usuário com o email já cadastrado
    //Se já existir ele irá retornar um usuário
    Optional<Usuario>getUserByEmail(String email);
}
