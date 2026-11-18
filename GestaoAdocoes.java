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

    public static SolicitacaoAdocao getSolicitacaoPorId(String id, Usuario usuario) {
        if (usuario instanceof AdministradorAbrigo) {
            for (SolicitacaoAdocao soli : solicitacoes) {
                if (soli.getId().equals(id)) {
                    return soli;
                }
            }
            throw new SolicitacaoNaoEncontradaException("Erro: Não foi encontrada nenhuma solicitação de adoção com o ID informado");
        } else {
            throw new UsuarioNaoPossuiAutorizacaoException("Erro: Somente um administrador do abrigo pode analisar solicitações de adoção.");
        }

    }

    public static ArrayList<SolicitacaoAdocao> getSolicitacoesPendentes(Usuario usuario) {
        ArrayList<SolicitacaoAdocao> copiaSolicitacoes  = new ArrayList<>();
        if (usuario instanceof AdministradorAbrigo) {
            for (SolicitacaoAdocao solicitacao : solicitacoes) {
                if (solicitacao.getStatusSolicitacao() == StatusSolicitacao.PENDENTE) {
                    copiaSolicitacoes.add(solicitacao);
                }
            }
            if (copiaSolicitacoes.isEmpty()) {
                throw new NaoHaSolicitacoesPendentesException("Não há solicitações de adoção pendentes de aprovação no momento.");
            }
            return copiaSolicitacoes;
        } else {
            throw new UsuarioNaoPossuiAutorizacaoException("Erro: Somente um administrador do abrigo pode analisar solicitações de adoção.");
        }
    }

    public static void avaliarSolicitacao(Usuario usuario, String idSolicitacao, boolean aprovada) {
        SolicitacaoAdocao solicitacaoAdocao = getSolicitacaoPorId(idSolicitacao, usuario);
        if (solicitacaoAdocao.getStatusSolicitacao() != StatusSolicitacao.PENDENTE) {
            throw new SolicitacaoJaAvaliadaException("Erro: Essa solicitação de adoção já foi avaliada.");
        }

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
        if (solicitacaoAdocao.getStatusSolicitacao() == StatusSolicitacao.APROVADA) {
            if (usuario instanceof AdministradorAbrigo) {
                solicitacaoAdocao.setStatusSolicitacao(StatusSolicitacao.DEVOLVIDA);
                solicitacaoAdocao.setObservacoes(motivo);
                solicitacaoAdocao.getAnimal().setStatusAdocao(StatusAdocao.DISPONIVEL);
            } else {
                throw new UsuarioNaoPossuiAutorizacaoException("Erro: Somente um administrador do abrigo pode registrar devoluções.");
            }
        } else {
            throw new SolicitacaoNaoFoiAprovadaException("Erro: Devoluções só podem ser registradas em solicitações que foram aprovadas previamente.");
        }

    }

    public static void imprimirSolicitacoes(Usuario usuario) {
        if (usuario instanceof AdministradorAbrigo) {
            for (SolicitacaoAdocao solicitacao : solicitacoes) {
                System.out.println(solicitacao.getResumo());
            }
        } else {
            throw new UsuarioNaoPossuiAutorizacaoException("Erro: Somente um administrador do abrigo pode analisar solicitações de adoção.");
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
