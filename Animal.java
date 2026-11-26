import java.util.ArrayList;

public class Animal {

    private static int contadorId = 0;
    public static final int MESES_DO_ANO = 12;

    private final String id;
    private String nome;
    private Especie especie;
    private String raca;
    private int idade; // em meses
    private Porte porte;
    private EstadoSaude estadoSaude;
    private Data dataResgate;
    private StatusAdocao statusAdocao;
    private boolean acompanhado;
    private ArrayList<RegistroMedico> historicoRegistrosMedicos;

    public Animal(String nome, Especie especie, String raca, int idade, Porte porte, EstadoSaude estadoSaude, Data dataResgate, StatusAdocao statusAdocao) {
        contadorId++;
        this.id = String.valueOf(contadorId);
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.porte = porte;
        this.estadoSaude = estadoSaude;
        this.dataResgate = dataResgate;
        this.statusAdocao = statusAdocao;
        this.acompanhado = false;
        historicoRegistrosMedicos = new ArrayList<>();
    }

    public ArrayList<RegistroMedico> getHistoricoRegistrosMedicos() {
        ArrayList<RegistroMedico> copiaHistorico = new ArrayList<>();
        for (RegistroMedico registro : historicoRegistrosMedicos) {
            copiaHistorico.add(registro);
        }
        return copiaHistorico;
    }

    public void adicionarRegistroMedico(RegistroMedico registro) {
        this.historicoRegistrosMedicos.add(registro);
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public Especie getEspecie() {
        return this.especie;
    }

    public String getRaca() {
        return this.raca;
    }

    public int getIdade() {
        return this.idade;
    }

    public int getIdadeEmAnos() {
        return (int) this.idade / MESES_DO_ANO;
    }

    public Porte getPorte() {
        return this.porte;
    }

    public EstadoSaude getEstadoSaude() {
        return this.estadoSaude;
    }

    public Data getDataResgate() {
        return this.dataResgate;
    }

    public StatusAdocao getStatusAdocao() {
        return this.statusAdocao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public void setEstadoSaude(EstadoSaude estadoSaude) {
        this.estadoSaude = estadoSaude;
    }

    public void setDataResgate(Data dataResgate) {
        this.dataResgate = dataResgate;
    }

    public void setStatusAdocao(StatusAdocao statusAdocao) {
        this.statusAdocao = statusAdocao;
    }

    public boolean isAcompanhado() {
        return this.acompanhado;
    }

    public void setAcompanhado(boolean acompanhado) {
        this.acompanhado = acompanhado;
    }
}