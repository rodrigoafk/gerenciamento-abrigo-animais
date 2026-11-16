import java.util.ArrayList;

public class AdministradorAbrigo extends Usuario {

    public AdministradorAbrigo(String nome, String cpf, String email, Data dataNascimwento, String senha) {
        super(nome, cpf, email, dataNascimwento, senha);
    }

    @Override
    public void exibirMenu() {

    }

    @Override
    public ArrayList<Animal> getListaAnimaisVisiveis() {
        return GestaoAnimais.getTodosAnimais();
    }
}
