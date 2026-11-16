import java.util.ArrayList;

public class Adotante extends Usuario {
    public Adotante(String nome, String cpf, String email, Data dataNascimento, String senha) {
        super(nome, cpf, email, dataNascimento, senha);
    }

    @Override
    public void exibirMenu() {

    }

    @Override
    public ArrayList<Animal> getListaAnimaisVisiveis() {
        return GestaoAnimais.animaisDisponiveis();
    }
}
