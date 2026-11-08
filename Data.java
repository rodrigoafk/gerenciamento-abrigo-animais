public class Data {

    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) throws IllegalArgumentException{
        if ((dia > 0 && dia <= 31) &&
                (mes > 0 && mes <= 12) &&
                (ano >= 2000 && ano <= 2026)) {
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;
        } else {
            throw new IllegalArgumentException("Ocorreu um erro. Data inválida");
        }
    }

    @Override
    public String toString() {
        return this.dia + "/" + this.mes + "/" + this.ano;
    }
}
