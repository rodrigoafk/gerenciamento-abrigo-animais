import java.util.ArrayList;

public interface Notificavel {
    void receberNotificacao(String notificacao);
    ArrayList<String> getNotificacoes();
}
