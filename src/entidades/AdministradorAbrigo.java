package entidades;

import gestao.GestaoAnimais;
import gestao.GestaoUsuarios;
import principal.Menu;


import java.util.ArrayList;

public class AdministradorAbrigo extends Usuario {

    public AdministradorAbrigo(String nome, String cpf, String email, Data dataNascimento, String senha) {
        super(nome, cpf, email, dataNascimento, senha);
    }

    @Override
    public void exibirMenu() {
        Menu.exibirMenuAdministradorAbrigo(this);
    }

    @Override
    public ArrayList<Animal> getListaAnimaisVisiveis() {
        return GestaoAnimais.getTodosAnimais();
    }

    public void emitiAvisoGeral(String notificacao) {
        GestaoUsuarios.enviarNotificacaoTodosUsuarios(notificacao);
    }

}
