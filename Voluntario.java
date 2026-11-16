import java.util.ArrayList;

public class Voluntario extends Usuario {

    private ArrayList<String> historicoParticipacoes;

    public Voluntario(String nome, String cpf, String email, Data dataNascimento, String senha) {
        super(nome, cpf, email, dataNascimento, senha);
        historicoParticipacoes = new ArrayList<>();
    }

    public void adicionarParticipacao(String participacao) {
        this.historicoParticipacoes.add(participacao);
    }

    public void historicoVazio() {
        if (historicoParticipacoes.isEmpty()) throw new HistoricoParticipacoesVazioException("O histórico de participações do voluntário está vazio");
    }

    @Override
    public void exibirMenu() {

    }

    @Override
    public ArrayList<Animal> getListaAnimaisVisiveis() {
        return GestaoAnimais.animaisDisponiveis();
    }
}
