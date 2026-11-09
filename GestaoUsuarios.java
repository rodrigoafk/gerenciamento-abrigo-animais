import java.util.ArrayList;

import static java.lang.Character.*;

public class GestaoUsuarios {

    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static boolean usuarioJaCadastrado(Usuario usuario) {
        boolean loginJaExiste = false;
        for (Usuario usu : usuarios) {
            if (usuario.getLogin().equals(usu.getLogin())) loginJaExiste = true;
        }
        return loginJaExiste;
    }

    public static boolean senhaValida(String senha) {
        if (senha == null) return false;
        int tamanho = senha.length();
        boolean temMaiuscula = false, temMinuscula = false, temNumero = false;
        if (tamanho < 8) return false;
        for (int i = 0; i < tamanho; i++) {
            char caractere = senha.charAt(i);
            if (isUpperCase(caractere)) temMaiuscula = true;
            if (isLowerCase(caractere)) temMinuscula = true;
            if (isDigit(caractere)) temNumero = true;
        }
        if (temMaiuscula && temMinuscula && temNumero) return true;
        else return false;
    }

    public static void cadastrarUsuario(Usuario usuario) {
        if (usuarioJaCadastrado(usuario)) {
            throw new UsuarioJaCadastradoException("Erro. Já existe um usuário cadastrado com esse login.");
        }
        if (senhaValida(usuario.getSenha())) {
            usuarios.add(usuario);
        } else {
            throw new SenhaInvalidaException("A senha deve possuir no mínimo 8 caracteres, sendo no mínimo uma letra maiúscula, uma letra minúscula e um número.");
        }


    }
}
