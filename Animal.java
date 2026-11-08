

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

}