import java.util.ArrayList;

public abstract class Usuario {
    private String nome;
    private String cpf;
    private String email;
    private Data dataNascimento;
    private String senha;

    public Usuario(String nome, String cpf, String email, Data dataNascimento, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public abstract void exibirMenu();

    public abstract ArrayList<Animal> getListaAnimaisVisiveis();

    public String getResumo() {
        return String.format("NOME: %s\nCPF: %s\nEMAIL: %s\n", getNome().toUpperCase(), getCpf(), getEmail());
    }
}
