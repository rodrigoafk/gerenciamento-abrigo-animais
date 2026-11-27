package entidades;

public class Consulta extends RegistroMedico {
    private String diagnostico;

    public Consulta(Data dataRealizacao, Veterinario veterinarioResponsavel, String descricao, String diagnostico) {
        super(dataRealizacao, veterinarioResponsavel, descricao);
        this.diagnostico = diagnostico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    @Override
    public String getResumo() {
        String retorno = String.format("Descrição: %s \nentidades.Data da consulta: %s \nVeterinário responsável: %s \nDiagnóstico: %s\n", getDescricao(), getDataRealizacao(), getVeterinarioResponsavel().getNome(), getDiagnostico());
        return retorno;
    }
}
