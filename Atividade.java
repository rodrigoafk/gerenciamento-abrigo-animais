import java.util.ArrayList;

public class Atividade {
    private static int contadorAtividades = 0;

    private final String id;
    private String nome;
    private TipoAtividade tipoAtividade;
    private Data data;
    private ArrayList<Voluntario> inscritos;

    public Atividade(String nome, TipoAtividade tipoAtividade, Data data) {
        contadorAtividades++;
        this.id = String.valueOf(contadorAtividades);
        this.nome = nome;
        this.tipoAtividade = tipoAtividade;
        this.data = data;
        inscritos = new ArrayList<>();
    }

    public String getResumo() {
        return String.format("ID: %s\nNOME: %s\nTIPO: %s\nDATA: %s\n", getId(), getNome(), getTipoAtividade(), getData());
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoAtividade getTipoAtividade() {
        return this.tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public ArrayList<Voluntario> getInscritos() {
        return this.inscritos;
    }

    public void setInscritos(ArrayList<Voluntario> inscritos) {
        this.inscritos = inscritos;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
