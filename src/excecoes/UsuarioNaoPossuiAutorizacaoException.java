package excecoes;

public class UsuarioNaoPossuiAutorizacaoException extends RuntimeException {
    public UsuarioNaoPossuiAutorizacaoException(String message) {
        super(message);
    }
}
