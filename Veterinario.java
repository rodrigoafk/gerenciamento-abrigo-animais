import java.util.ArrayList;

public class Veterinario extends Usuario {

    public Veterinario(String nome, String cpf, String email, Data dataNascimento, String senha) {
        super(nome, cpf, email, dataNascimento, senha);
    }

    @Override
    public void exibirMenu() {

    }

    @Override
    public ArrayList<Animal> getListaAnimaisVisiveis() {
        return GestaoAnimais.getTodosAnimais();
    }
}
