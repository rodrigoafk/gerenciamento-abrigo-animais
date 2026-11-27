package entidades;

public class Medicamento extends RegistroMedico {
    private String dosagem;
    private int duracaoDias;

    public int getDuracaoDias() {
        return duracaoDias;
    }

    public void setDuracaoDias(int duracaoDias) {
        this.duracaoDias = duracaoDias;
    }

    public Medicamento(Data dataRealizacao, Veterinario veterinarioResponsavel, String descricao, String dosagem, int duracaoDias) {
        super(dataRealizacao, veterinarioResponsavel, descricao);
        this.dosagem = dosagem;
        this.duracaoDias = duracaoDias;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    @Override
    public String getResumo() {
        String retorno = String.format("Descrição: %s \nentidades.Data de início: %s \nVeterinário responsável: %s \nDosagem: %s\n Dias de tratamento: %d\n", getDescricao(), getDataRealizacao(), getVeterinarioResponsavel().getNome(), getDosagem(), getDuracaoDias());
        return retorno;
    }

}

