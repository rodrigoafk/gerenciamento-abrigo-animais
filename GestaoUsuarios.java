import java.util.ArrayList;

import static java.lang.Character.*;

public class GestaoUsuarios {
    public static final int NUMERO_DIGITOS_CPF = 11;

    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void cpfValido(String cpf) {
        int tamanhoCpf = cpf.length();
        if (tamanhoCpf != NUMERO_DIGITOS_CPF) {
            throw new CPFInvalidoException("Erro: CPF inválido. O documento deve possuir 11 números");
        }

        if (usuarioJaCadastrado(cpf)) {
            throw new UsuarioJaCadastradoException("Erro. Já há um usuário cadastrado com esse CPF. Tente fazer login.");
        }
        boolean temOutroCaractere = false;
        for (int i = 0; i < tamanhoCpf; i++) {
            char caractere = cpf.charAt(i);
            if (!isDigit(caractere)) { // não é número
                temOutroCaractere = true;
            }
        }
        if (temOutroCaractere) {
            throw new CPFInvalidoException("Erro. O CPF deve possuir somente números.");
        }

    }

    public static boolean usuarioJaCadastrado(String cpf) {
        boolean cpfJaExiste = false;
        for (Usuario usu : usuarios) {
            if (cpf.equals(usu.getCpf())) cpfJaExiste = true;
        }
        return cpfJaExiste;
    }

    public static void senhaValida(String senha) {
        if (senha == null) throw new SenhaInvalidaException("Erro: A senha informada é nula.");
        int tamanho = senha.length();
        boolean temMaiuscula = false, temMinuscula = false, temNumero = false;
        if (tamanho < 8) {
            throw new SenhaInvalidaException("Erro: A senha informada possui menos de 8 caracteres");
        }
        for (int i = 0; i < tamanho; i++) {
            char caractere = senha.charAt(i);
            if (isUpperCase(caractere)) temMaiuscula = true;
            if (isLowerCase(caractere)) temMinuscula = true;
            if (isDigit(caractere)) temNumero = true;
        }
        if (temMaiuscula == false || temMinuscula == false || temNumero == false) {
            throw new SenhaInvalidaException("A senha deve possuir no mínimo 8 caracteres, sendo no mínimo uma letra maiúscula, uma letra minúscula e um número.");
        }
    }

    public static void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static Usuario autenticarUsuario(String cpf, String senha) {

        if (usuarioJaCadastrado(cpf)) {
            for (Usuario usu : usuarios) {
                if (cpf.equals(usu.getCpf())) {
                    if (senha.equals(usu.getSenha())) {
                        return usu;
                    }
                }
            }
        }
        throw new CredenciaisInvalidasException("Erro: Credenciais inválidas. O login falhou");
    }
}
