package entidades;

import enums.StatusSolicitacao;

public class SolicitacaoAdocao {
    private static int contadorSolicitacoes = 0;

    private final String id;
    private Adotante adotante;
    private Animal animal;
    private Data dataSolicitacao;
    private StatusSolicitacao statusSolicitacao;
    private String observacoes;


    public SolicitacaoAdocao(Adotante adotante, Animal animal, Data dataSolicitacao) {
        contadorSolicitacoes++;
        this.id = String.valueOf(contadorSolicitacoes);
        this.adotante = adotante;
        this.animal = animal;
        this.dataSolicitacao = dataSolicitacao;
        this.statusSolicitacao = StatusSolicitacao.PENDENTE;
        this.observacoes = "Nenhuma";
    }

    public String getObservacoes() {
        return this.observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getId() {
        return this.id;
    }

    public Adotante getAdotante() {
        return this.adotante;
    }

    public void setAdotante(Adotante adotante) {
        this.adotante = adotante;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Data getDataSolicitacao() {
        return this.dataSolicitacao;
    }

    public void setDataSolicitacao(Data dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public StatusSolicitacao getStatusSolicitacao() {
        return this.statusSolicitacao;
    }

    public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public String getResumo() {
        String retorno = String.format("ID da solicitação: %s \nNome do adotante: %s \nNome do animal: %s \nID do animal: %s \nData de solicitação %s\nStatus da solicitação: %s\n", this.getId(), this.getAdotante().getNome(), this.getAnimal().getNome(), this.getAnimal().getId(), this.getDataSolicitacao(), this.getStatusSolicitacao());
        if (this.getStatusSolicitacao().equals(StatusSolicitacao.DEVOLVIDA)) {
            retorno = retorno + "Motivo da devolução: " + this.getObservacoes() + "\n";
        }
        return retorno;
    }
}
