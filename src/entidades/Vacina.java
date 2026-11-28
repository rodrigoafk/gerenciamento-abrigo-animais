package entidades;

public class Vacina extends RegistroMedico {
    private Data dataValidade;

    public Vacina(Data dataRealizacao, Veterinario veterinarioResponsavel, String descricao, Data dataValidade) {
        super(dataRealizacao, veterinarioResponsavel, descricao);
        this.dataValidade = dataValidade;
    }

    public Data getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Data dataValidade) {
        this.dataValidade = dataValidade;
    }

    @Override
    public String getResumo() {
        String retorno = String.format("Descrição: %s \nData de aplicação: %s\nData de validade: %s \nVeterinário responsável: %s\n", getDescricao(), getDataRealizacao(), getDataValidade(), getVeterinarioResponsavel().getNome());
        return retorno;
    }

    public int getValidadeEmDias() {
        return this.dataValidade.getTotalEmDias();
    }
}
