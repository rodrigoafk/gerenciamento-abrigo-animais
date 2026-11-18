import java.util.ArrayList;

public class GestaoAdocoes {
    private static ArrayList<SolicitacaoAdocao> solicitacoes = new ArrayList<>();

    public static void solicitarAdocao(Adotante adotante, Animal animal, Data data) {
        if (animal.getStatusAdocao() == StatusAdocao.ADOTADO ) {
            throw new AnimalIndisponivelException("Erro: O animal selecionado já foi adotado.");
        }
        if (animal.getEstadoSaude() == EstadoSaude.EM_TRATAMENTO_CRITICO) {
            throw new AnimalIndisponivelException("Erro: O animal selecionado está em tratamento crítico e não pode receber solicitações de adoção no momento");
        }
        SolicitacaoAdocao solicitacao = new SolicitacaoAdocao(adotante, animal, data);
        solicitacoes.add(solicitacao);
    }

    public static void avaliarSolicitacao(Usuario usuario, SolicitacaoAdocao solicitacaoAdocao, boolean aprovada) {
        if (usuario instanceof AdministradorAbrigo) {
            if (aprovada == true) {
                solicitacaoAdocao.setStatusSolicitacao(StatusSolicitacao.APROVADA);
                solicitacaoAdocao.getAnimal().setStatusAdocao(StatusAdocao.ADOTADO);
            } else {
                solicitacaoAdocao.setStatusSolicitacao(StatusSolicitacao.REJEITADA);
            }
            return;
        }

        throw new UsuarioNaoPossuiAutorizacaoException("Erro: Somente um administrador do abrigo pode avaliar solicitações de adoção.");
    }

    public static void registrarDevolucao(Usuario usuario, SolicitacaoAdocao solicitacaoAdocao, String motivo)  {
        if (usuario instanceof AdministradorAbrigo) {
            solicitacaoAdocao.setStatusSolicitacao(StatusSolicitacao.DEVOLVIDA);
            solicitacaoAdocao.setObservacoes(motivo);
            solicitacaoAdocao.getAnimal().setStatusAdocao(StatusAdocao.DISPONIVEL);
        } else {
            throw new UsuarioNaoPossuiAutorizacaoException("Erro: Somente um administrador do abrigo pode registrar devoluções.");
        }

    }

    public static void imprimirSolicitacoes() {
        for (SolicitacaoAdocao solicitacao : solicitacoes) {
            System.out.println(solicitacao.getResumo());
        }
    }

    public static void verificarAdocoesCpf(Usuario usuario) {
        String cpfUsuario = usuario.getCpf();
        for (SolicitacaoAdocao solicitacaoAdocao : solicitacoes) {
            if (solicitacaoAdocao.getAdotante().getCpf().equals(cpfUsuario)) {
                System.out.println(solicitacaoAdocao.getResumo());
                System.out.println("========================================\n");
            }
        }
    }

    }
