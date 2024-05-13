package Modelos;

public class Conversor {

    private final String valorBase;

    private final String valorAlvo;

    private final Float rate;
    private final Float resultado;

    public Conversor(ConversorAPI conversor) {
        this.valorBase = conversor.base_code();
        this.valorAlvo = conversor.target_code();
        this.rate = conversor.conversion_rate();
        this.resultado = conversor.conversion_result();
    }


    @Override
    public String toString() {
        return  "valorBase = '" + valorBase + '\'' +
                ", valorAlvo = '" + valorAlvo + '\'' +
                ", câmbio = '" + rate + '\'' +
                ", resultado = '" + resultado + '\'';
    }
}
