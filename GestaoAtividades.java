import java.util.ArrayList;

public class GestaoAtividades {
    private static ArrayList<Atividade> atividades = new ArrayList<>();

    public static ArrayList<Atividade> getAtividades() {
        if (atividades.size() == 0) {
            throw new NenhumaAtividadeCadastradaException("Nenhuma atividade foi cadastrada no sistema até o momento");
        }
        ArrayList<Atividade> copiaAtividades = new ArrayList<Atividade>();
        for (Atividade atividade : atividades) {
            copiaAtividades.add(atividade);
        }
        return copiaAtividades;
    }

    public static void imprimirTodasAtividades() {
        if (atividades.size() == 0) {
            throw new NenhumaAtividadeCadastradaException("Nenhuma atividade foi cadastrada no sistema até o momento");
        }
        for (Atividade atividade : atividades) {
            System.out.println("\n" + atividade.getResumo() + "\n");
        }
    }

    public static Atividade getAtividadeId(String idRecebido) {
        for (Atividade atividade : atividades) {
            if (atividade.getId().equals(idRecebido)) {
                return atividade;
            }
        }
        throw new AtividadeNaoEncontradaException("Erro: Não foi encontrada nenhuma atividade com o ID informado.");
    }

    public static boolean voluntarioJaInscritoAtividade(Usuario usuario, Atividade atividadeInformada) {
        String cpfVoluntario = usuario.getCpf();
        String idAtividade = atividadeInformada.getId();
        boolean atividadeEncontrada = false;

        for (Atividade atividade : atividades) {
            if (atividade.getId().equals(idAtividade))  {
                atividadeEncontrada = true;
                ArrayList<Voluntario> inscritos = atividade.getInscritos();
                for (Voluntario inscrito : inscritos) {
                    if (inscrito.getCpf().equals(cpfVoluntario)) return true;
                }
                return false;
            }

        }
        throw new AtividadeNaoEncontradaException("Erro: Não foi encontrada nenuma atividade com o ID informado.");
    }

    public static void inscreverVoluntarioAtividade(Voluntario voluntario, String idAtividade) {
        Atividade atividade = getAtividadeId(idAtividade);
        boolean jaInscrito = voluntarioJaInscritoAtividade(voluntario, atividade);
        if (jaInscrito) throw new VoluntarioJaInscritoException("Erro: O voluntário já está inscrito nessa atividade.");
        atividade.getInscritos().add(voluntario);
        voluntario.adicionarParticipacao(atividade);
    }

    public static void cadastrarAtividade(Usuario usuario, String nome, TipoAtividade tipoAtividade, Data data) {
        if (usuario instanceof AdministradorAbrigo) {
            Atividade atividade = new Atividade(nome, tipoAtividade, data);
            atividades.add(atividade);
        } else {
            throw new UsuarioNaoPossuiAutorizacaoException("Erro: Somente um administrador do abrigo pode cadastrar novas atividades.");
        }
    }
}
