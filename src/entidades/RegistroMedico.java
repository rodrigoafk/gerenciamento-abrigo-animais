package entidades;

public abstract class RegistroMedico {
    private Data dataRealizacao;
    private Veterinario veterinarioResponsavel;
    private String descricao;

    public RegistroMedico(Data dataRealizacao, Veterinario veterinarioResponsavel, String descricao) {
        this.dataRealizacao = dataRealizacao;
        this.veterinarioResponsavel = veterinarioResponsavel;
        this.descricao = descricao;
    }

    public Data getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Data dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Veterinario getVeterinarioResponsavel() {
        return veterinarioResponsavel;
    }

    public void setVeterinarioResponsavel(Veterinario veterinarioResponsavel) {
        this.veterinarioResponsavel = veterinarioResponsavel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public abstract String getResumo();


}
