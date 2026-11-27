package entidades;

import excecoes.AnimalIndisponivelException;
import excecoes.HistoricoParticipacoesVazioException;
import gestao.GestaoAnimais;
import principal.Menu;

import java.util.ArrayList;

public class Voluntario extends Usuario {


    private ArrayList<Atividade> historicoParticipacoes;
    private ArrayList<Animal> animaisAcompanhados;

    public Voluntario(String nome, String cpf, String email, Data dataNascimento, String senha) {
        super(nome, cpf, email, dataNascimento, senha);
        historicoParticipacoes = new ArrayList<>();
        animaisAcompanhados = new ArrayList<>();
    }

    public void adicionarParticipacao(Atividade atividade) {
        this.historicoParticipacoes.add(atividade);
    }

    public void historicoVazio() {
        if (historicoParticipacoes.isEmpty()) throw new HistoricoParticipacoesVazioException("O histórico de participações do voluntário está vazio");
    }

    public ArrayList<String> getHistoricoParticipacoes() {
        ArrayList<String> copiaHistorico = new ArrayList<>();
        for (Atividade atividade : historicoParticipacoes) {
            copiaHistorico.add(atividade.getResumo());
        }
        return copiaHistorico;
    }

    @Override
    public void exibirMenu() {
        Menu.exibirMenuVoluntario(this);

    }

    @Override
    public ArrayList<Animal> getListaAnimaisVisiveis() {
        return GestaoAnimais.animaisDisponiveis();
    }

    public void acompanharAnimal(Animal animal) {
        if (animal.isAcompanhado()) {
            throw new AnimalIndisponivelException("Erro: O animal já está sendo acompanhado no momento.");
        }
        animal.setAcompanhado(true);
        animaisAcompanhados.add(animal);
    }

    public ArrayList<Animal> getAnimaisAcompanhados() {
        ArrayList<Animal> copia = new ArrayList<>();
        for (Animal animal : animaisAcompanhados) {
            copia.add(animal);
        }
        return copia;
    }




}
