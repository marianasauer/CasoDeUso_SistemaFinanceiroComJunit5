package infra;

import domain.Usuario;
import services.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioMemoryRepository implements UsuarioRepository {
   private List<Usuario> users;
   private Long currentId;

    public UsuarioMemoryRepository() {
        currentId = 0L;
        users = new ArrayList<>();
        salvar(new Usuario(null, "User #1", "user@mail.com", "12345"));
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        Usuario newUser = new Usuario(nextId(), usuario.nome(), usuario.email(), usuario.senha());
        users.add(newUser);
        return newUser;
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.email().equalsIgnoreCase(email))
                .findFirst();
    }

    public  void printUsers(){
        System.out.println(users);
    }
    private Long nextId(){
        return ++currentId;
    }

    public static void main(String[] args) {
        UsuarioMemoryRepository repo = new UsuarioMemoryRepository();
        repo.printUsers();
        repo.salvar(new Usuario(null, "Usuário qualquer", "a@a.com", "aaa"));
        repo.printUsers();
    }
}
