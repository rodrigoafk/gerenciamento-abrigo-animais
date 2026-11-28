package entidades;

public class Data {

    private int dia;
    private int mes;
    private int ano;

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Data(int dia, int mes, int ano) {
        if ((dia > 0 && dia <= 31) &&
                (mes > 0 && mes <= 12) &&
                (ano >= 1926 && ano <= 2050)) {
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;
        } else {
            throw new IllegalArgumentException("Erro: Data inválida.");
        }
    }

    @Override
    public String toString() {
        return this.dia + "/" + this.mes + "/" + this.ano;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Data)) return false;

        Data outra = (Data) obj; // converte para Data para acessar os atributos
        return this.dia == outra.dia && this.mes == outra.mes && this.ano == outra.ano;
    }

    public int getTotalEmDias() {
        return (getAno() * 365) + (getMes() * 30) + getDia();
    }
}
