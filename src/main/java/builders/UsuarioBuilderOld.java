package builders;

import org.example.Usuario;

public class UsuarioBuilderOld {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private UsuarioBuilderOld(){}

    public static UsuarioBuilderOld umUsuario(){
        UsuarioBuilderOld builder = new UsuarioBuilderOld();
        inicializarDadosPadroes(builder);
        return builder;
    }

    private static void inicializarDadosPadroes(UsuarioBuilderOld builder) {
        builder.id = 1L;
        builder.nome = "Usuário Válido";
        builder.email = "user@mail.com";
        builder.senha = "12345678";
    }

    public UsuarioBuilderOld comId(Long param){
        id = param;
        return this;
    }

    public UsuarioBuilderOld comNome(String param){
        nome = param;
        return this;
    }

    public UsuarioBuilderOld comEmail(String param){
        email = param;
        return this;
    }

    public UsuarioBuilderOld comSenha(String param){
        senha = param;
        return this;
    }

    public Usuario agora(){
        return new Usuario(id, nome, email,senha);
    }
}
