

public class Animal {
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private Porte porte;
    private EstadoSaude estadoSaude;
    private Data dataResgate;
    private StatusAdocao statusAdocao;

    public Animal(String nome, String especie, String raca, int idade, Porte porte, EstadoSaude estadoSaude, Data dataResgate, StatusAdocao statusAdocao) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.porte = porte;
        this.estadoSaude = estadoSaude;
        this.dataResgate = dataResgate;
        GestaoAnimais.addAnimal(this);
    }

    public String getNome() {
        return this.nome;
    }

    public String getEspecie() {
        return this.especie;
    }

    public String getRaca() {
        return this.raca;
    }

    public int getIdade() {
        return this.idade;
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

    public void setEspecie(String especie) {
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
}