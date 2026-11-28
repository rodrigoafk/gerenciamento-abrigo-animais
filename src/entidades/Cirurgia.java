package entidades;

public class Cirurgia extends RegistroMedico {
    private String cuidadosPosOperatorio;

    public Cirurgia(Data dataRealizacao, Veterinario veterinarioResponsavel, String descricao, String cuidadosPosOperatorio) {
        super(dataRealizacao, veterinarioResponsavel, descricao);
        this.cuidadosPosOperatorio = cuidadosPosOperatorio;
    }

    public String getCuidadosPosOperatorio() {
        return this.cuidadosPosOperatorio;
    }

    public void setCuidadosPosOperatorio(String cuidadosPosOperatorio) {
        this.cuidadosPosOperatorio = cuidadosPosOperatorio;
    }

    @Override
    public String getResumo() {
        String retorno = String.format("Descrição: %s \nData da cirurgia: %s \nVeterinário responsável: %s \nCuidados pós-operatório: %s\n", getDescricao(), getDataRealizacao(), getVeterinarioResponsavel().getNome(), getCuidadosPosOperatorio());
        return retorno;
    }
}
