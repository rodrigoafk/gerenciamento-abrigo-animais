public class Voluntario extends Usuario {
    public Voluntario(String nome, String cpf, String email, Data dataNascimento, String login, String senha) {
        super(nome, cpf, email, dataNascimento, login, senha);
        GestaoUsuarios.cadastrarUsuario(this);
    }

    @Override
    public void exibirMenu() {

    }
}
