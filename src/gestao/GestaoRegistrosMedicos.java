package gestao;

import entidades.*;
import enums.EstadoSaude;
import excecoes.UsuarioNaoPossuiAutorizacaoException;
import excecoes.VacinaDuplicadaException;

import java.util.ArrayList;

public class GestaoRegistrosMedicos {

    public static void usuarioPossuiAutorizacao(Usuario usuario) {
        if (usuario instanceof Veterinario) {
            return;
        }
        throw new UsuarioNaoPossuiAutorizacaoException("Erro: Somente veterinários podem atualizar os registros médicos dos animais.");
    }

    public static void imprimirRegistrosLista(ArrayList<RegistroMedico> registros) {
        for (RegistroMedico r : registros) {
            System.out.println(r.getResumo());
        }
    }

    public static void registrarVacina(Animal animal, Usuario usuario, Data dataRealizacao, Veterinario veterinarioResponsavel, String descricao, Data dataValidade) {
        usuarioPossuiAutorizacao(usuario);
        Vacina vacina = new Vacina(dataRealizacao, veterinarioResponsavel, descricao, dataValidade);
        verificarVacinaDuplicada(animal, vacina.getDataRealizacao());
        animal.adicionarRegistroMedico(vacina);
    }

    public static void registrarConsulta(Animal animal, Usuario usuario, Data dataRealizacao, Veterinario veterinario, String descricao, String diagnostico) {
        usuarioPossuiAutorizacao(usuario);
        Consulta consulta = new Consulta(dataRealizacao, veterinario, descricao, diagnostico);
        animal.adicionarRegistroMedico(consulta);
    }

    public static void registrarCirurgia(Animal animal, Usuario usuario,  Data dataRealizacao, Veterinario veterinario, String descricao, String cuidadosPosOperatorio) {
        usuarioPossuiAutorizacao(usuario);
        Cirurgia cirurgia = new Cirurgia(dataRealizacao, veterinario, descricao, cuidadosPosOperatorio);
        animal.adicionarRegistroMedico(cirurgia);
    }

    public static void registrarMedicamento(Animal animal, Usuario usuario, Data dataRealizacao, Veterinario veterinario, String descricao, String dosagem, int duracaoDias) {
        usuarioPossuiAutorizacao(usuario);
        Medicamento medicamento = new Medicamento(dataRealizacao, veterinario, descricao, dosagem, duracaoDias);
        animal.adicionarRegistroMedico(medicamento);
    }

    public static void verificarVacinaDuplicada(Animal animal, Data dataVacina) {
         ArrayList<RegistroMedico> registro = animal.getHistoricoRegistrosMedicos();
         for (RegistroMedico r : registro) {
             if (r instanceof Vacina) {
                 if (dataVacina.equals(r.getDataRealizacao())) {
                     throw new VacinaDuplicadaException("Erro: Não é permitido registrar vacinação duplicada na mesma data");
                 }
             }
         }
    }

    public static void atualizarEstadoSaudeAnimal(Animal animal, EstadoSaude estadoSaude, Usuario usuario) {
        usuarioPossuiAutorizacao(usuario);
        if (usuario instanceof Veterinario) {
            animal.setEstadoSaude(estadoSaude);
        }
    }

    public static void verificarVacinasVencendo(Data data) {
        int diasDataAtual = data.getTotalEmDias();
        ArrayList<Animal> listaAnimais = GestaoAnimais.getTodosAnimais();
        for (Animal animal : listaAnimais) {
            for (RegistroMedico registro : animal.getHistoricoRegistrosMedicos()) {
                if (registro instanceof Vacina) {
                    int totalValidadeDias = ((Vacina) registro).getValidadeEmDias();
                    int diferencaDias = totalValidadeDias - diasDataAtual;
                    if (diferencaDias < 0) {
                        String msg = String.format("Alerta: A vacina %s do animal %s está vencida!", registro.getDescricao(), animal.getNome());
                        GestaoUsuarios.enviarNotificacaoVeterinarios(msg);
                    } else if (diferencaDias <= 3) {
                        String msg = String.format("Alerta: A validade da vacina %s do animal %s está próxima do fim! Restam %d dia(s).", registro.getDescricao(), animal.getNome(), diferencaDias);
                        GestaoUsuarios.enviarNotificacaoVeterinarios(msg);
                    }
                }
            }
        }
    }
}
