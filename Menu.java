import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Animal> listaFiltroAtual = new ArrayList<>();
    public static Data DATA_ATUAL;

    public static Data lerData() {
        System.out.print("Dia: ");
        int dia = scanner.nextInt();
        System.out.print("Mês: ");
        int mes = scanner.nextInt();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        Data data = new Data(dia, mes, ano);
        return data;
    }

    public static Animal selecionarAnimalPorId(ArrayList<Animal> listaVisivel) {
        System.out.print("\nInforme o ID do animal: ");
        String id = scanner.nextLine();

        try {
            Animal animal = GestaoAnimais.buscarAnimalId(id, listaVisivel);
            return animal;
        } catch (IdNaoLocalizadoException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean listaEstaVazia(ArrayList<Animal> lista) {
        if (lista == null || lista.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void consultarHistoricoMedicoAnimal(ArrayList<Animal> animaisVisiveis) {
        Animal animal = selecionarAnimalPorId(animaisVisiveis);
        if (animal == null) return;
        ArrayList<RegistroMedico> copiaHistorico = animal.getHistoricoRegistrosMedicos();
        GestaoRegistrosMedicos.imprimirRegistrosLista(copiaHistorico);
    }

    public static boolean continuarFiltrando() {
        int option = -1;
        do {
            System.out.println("============ FILTROS ============");
            System.out.println("| Selecione uma opção:          |");
            System.out.println("| 1 - Inserir mais filtros      |");
            System.out.println("| 2 - Obter resultados         |");
            System.out.println("===============================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option != 1 && option != 2);

        if (option == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Animal> menuPorte() {
        int porte = 0;
        do {
            System.out.println("=========== FILTRAR POR PORTE ===========");
            System.out.println("| Selecione uma opção:                  |");
            System.out.println("| 1 - PEQUENO                           |");
            System.out.println("| 2 - MÉDIO                             |");
            System.out.println("| 3 - GRANDE                            |");
            System.out.println("=========================================");
            System.out.print("| => ");
            porte = scanner.nextInt();
            scanner.nextLine();
        } while (porte < 1 || porte > 3);

        ArrayList<Animal> lista = new ArrayList<>();

        if (porte == 1) return GestaoAnimais.filtrarAnimaisPorPorte(Porte.PEQUENO, listaFiltroAtual);
        else if (porte == 2) return GestaoAnimais.filtrarAnimaisPorPorte(Porte.MEDIO, listaFiltroAtual);
        else return GestaoAnimais.filtrarAnimaisPorPorte(Porte.GRANDE, listaFiltroAtual);

    }

    public static ArrayList<Animal> menuEspecie() {
        int especie = 0;
        do {
            System.out.println("=========== FILTRAR POR ESPECIE ===========");
            System.out.println("| Selecione uma espécie:                  |");
            System.out.println("| 1 - Filtrar por CACHORRO                |");
            System.out.println("| 2 - Filtrar por GATO                    |");
            System.out.println("| 3 - Filtrar por ROEDOR                  |");
            System.out.println("| 4 - Filtrar por AVE                     |");
            System.out.println("===========================================");
            System.out.print("| => ");
            especie = scanner.nextInt();
            scanner.nextLine();
        } while (especie < 1 || especie > 4);

        if (especie == 1) {
            return GestaoAnimais.filtrarAnimaisPorEspecie(Especie.CACHORRO, listaFiltroAtual);
        } else if (especie == 2) {
            return GestaoAnimais.filtrarAnimaisPorEspecie(Especie.GATO, listaFiltroAtual);
        } else if (especie == 3) {
            return GestaoAnimais.filtrarAnimaisPorEspecie(Especie.ROEDOR, listaFiltroAtual);
        } else {
            return GestaoAnimais.filtrarAnimaisPorEspecie(Especie.AVE, listaFiltroAtual);
        }

    }

    public static ArrayList<Animal> menuRaca() {
        System.out.println("=========== FILTRAR POR RAÇA ===========");
        System.out.println("| Informe a raça a ser buscada:        |");
        System.out.println("========================================");
        System.out.print("| => ");
        String raca = scanner.nextLine();

        return GestaoAnimais.filtrarAnimaisPorRaca(raca, listaFiltroAtual);
    }

    public static ArrayList<Animal> menuIdade() {
        System.out.println("=========== FILTRAR POR FAIXA ETÁRIA ===========");
        System.out.println("| Informe a idade mínima (em anos):            |");
        System.out.print("| => ");
        int idadeMin = scanner.nextInt();
        System.out.println("| Informe a idade máxima (em anos):             ");
        System.out.print("| => ");
        int idadeMax = scanner.nextInt();
        scanner.nextLine();
        System.out.println("================================================");
        ArrayList<Animal> filtroFaixaEtaria = new ArrayList<>();
        try {
            filtroFaixaEtaria = GestaoAnimais.filtrarAnimaisPorIdade(idadeMin, idadeMax, listaFiltroAtual);
        } catch (BuscaInvalidaException e) {
            System.out.println(e.getMessage());
        }
        return filtroFaixaEtaria;
    }

    public static void menuBuscarAnimais(ArrayList<Animal> animaisVisiveis) {
        int option = -1;

        do {
            System.out.println("=========== BUSCA ==============");
            System.out.println("| Selecione uma opção:         |");
            System.out.println("| 1 - Buscar por ID            |");
            System.out.println("| 2 - Buscar por NOME          |");
            System.out.println("================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option != 1 && option != 2);

        if (option == 1) {
            System.out.println("================================");
            System.out.println("| Informe o ID:                |");
            System.out.print("| => ");
            String idBuscado = scanner.nextLine();

            try {
                ArrayList<Animal> listaAnimalId = new ArrayList<>();
                listaAnimalId.add(GestaoAnimais.buscarAnimalId(idBuscado, animaisVisiveis));
                GestaoAnimais.imprimirAnimaisLista(listaAnimalId);
            } catch (IdNaoLocalizadoException e) {
                System.out.println(e.getMessage());
                System.out.println("================================");
            }
        } else if (option == 2) {
            System.out.println("================================");
            System.out.println("| Informe o nome:              |");
            System.out.print("| => ");
            String nomeBuscado = scanner.nextLine();
            try {
                ArrayList<Animal> animaisNome = GestaoAnimais.buscarAnimaisNome(nomeBuscado, animaisVisiveis);
                GestaoAnimais.imprimirAnimaisLista(animaisNome);
            } catch (NomeNaoLocalizadoException e) {
                System.out.println(e.getMessage());
                System.out.println("================================");
            }
        }
    }

    public static void menuAcompanharAnimal(Voluntario voluntario) {
        int option = -1;
        do {
            System.out.println("============== ACOMPANHAMENTO DE ANIMAIS ==============");
            System.out.println("| 1 - Assumir acompanhamento de um animal             |");
            System.out.println("| 2 - Visualizar animais sob minha responsabilidade   |");
            System.out.println("| 0 - Voltar                                          |");
            System.out.println("=======================================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 0 || option > 2);

        if (option == 1) {
            ArrayList<Animal> desacompanhados = GestaoAnimais.getAnimaisDesacompanhados();
            if (listaEstaVazia(desacompanhados)) {
                System.out.println("Não há animais disponíveis sem acompanhamento no momento.");
                return;
            }
            GestaoAnimais.imprimirAnimaisLista(desacompanhados);
            Animal animal = selecionarAnimalPorId(desacompanhados);
            if (animal == null) return;

            try {
                voluntario.acompanharAnimal(animal);
                System.out.println("Animal vinculado ao seu acompanhamento com sucesso!");
            } catch (AnimalIndisponivelException e) {
                System.out.println(e.getMessage());
            }
        } else if (option == 2) {
            ArrayList<Animal> animaisSobMinhaResponsabilidade = voluntario.getAnimaisAcompanhados();
            if (listaEstaVazia(animaisSobMinhaResponsabilidade)) {
                System.out.println("Você ainda não está acompanhando nenhum animal.");
            } else {
                GestaoAnimais.imprimirAnimaisLista(animaisSobMinhaResponsabilidade);
            }
        }
    }

    public static Especie menuEscolherEspecie() {
        int option = -1;
        do {
            System.out.println("==================== ESPÉCIE ====================");
            System.out.println("| Selecione uma opção:                          |");
            System.out.println("| 1 - CACHORRO                                  |");
            System.out.println("| 2 - GATO                                      |");
            System.out.println("| 3 - ROEDOR                                    |");
            System.out.println("| 4 - AVE                                       |");
            System.out.println("=================================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 1 || option > 4);

        if (option == 1) return Especie.CACHORRO;
        else if (option == 2) return Especie.GATO;
        else if (option == 3) return Especie.ROEDOR;
        else return Especie.AVE;
    }

    public static int menuCadastrarIdade() {
        int option = -1;
        do {
            System.out.println("==================== IDADE ====================");
            System.out.println("| Selecione uma opção:                        |");
            System.out.println("| 1 - Informar em MESES                       |");
            System.out.println("| 2 - Informar em ANOS                        |");
            System.out.println("===============================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option != 1 && option != 2);

        System.out.println("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) return idade;
        else return idade * Animal.MESES_DO_ANO;
    }

    public static String lerUmCpf() {
        System.out.println("Informe o CPF do usuário buscado: ");
        String cpf = scanner.nextLine();
        return cpf;
    }

    public static Porte menuEscolherPorte() {
        int option = -1;
        do {
            System.out.println("==================== PORTE ====================");
            System.out.println("| Selecione uma opção:                        |");
            System.out.println("| 1 - PEQUENO                                 |");
            System.out.println("| 2 - MÉDIO                                   |");
            System.out.println("| 3 - GRANDE                                  |");
            System.out.println("===============================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 1 || option > 3);

        if (option == 1) return Porte.PEQUENO;
        else if (option == 2) return Porte.MEDIO;
        else return Porte.GRANDE;
    }

    public static EstadoSaude menuEscolherEstadoSaude() {
        int option = -1;
        do {
            System.out.println("=============== ESTADO DE SAÚDE ===============");
            System.out.println("| Selecione uma opção:                        |");
            System.out.println("| 1 - SAÚDAVEL                                |");
            System.out.println("| 2 - EM TRATAMENTO                           |");
            System.out.println("| 3 - EM TRATAMENTO CRÍTICO                   |");
            System.out.println("===============================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 1 || option > 3);

        if (option == 1) return EstadoSaude.SAUDAVEL;
        else if (option == 2) return EstadoSaude.EM_TRATAMENTO;
        else return EstadoSaude.EM_TRATAMENTO_CRITICO;
    }

    public static StatusAdocao menuEscolherStatusAdocao() {
        int option = -1;
        do {
            System.out.println("================ STATUS DE ADOÇÃO ================");
            System.out.println("| Selecione uma opção:                           |");
            System.out.println("| 1 - DISPONÍVEL                                 |");
            System.out.println("| 2 - ADOTADO                                    |");
            System.out.println("==================================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option != 1 && option != 2);

        if (option == 1) return StatusAdocao.DISPONIVEL;
        else return StatusAdocao.ADOTADO;
    }

    public static void menuCadastrarAnimal(AdministradorAbrigo administradorAbrigo) {
        System.out.print("Nome do animal: ");
        String nome = scanner.nextLine();
        Especie especie = menuEscolherEspecie();
        System.out.println("Raça do animal: ");
        String raca = scanner.nextLine();
        int idade = menuCadastrarIdade();
        Porte porte = menuEscolherPorte();
        EstadoSaude estadoSaude = menuEscolherEstadoSaude();
        Data dataResgate = lerData();
        StatusAdocao statusAdocao = menuEscolherStatusAdocao();
        Animal animal = new Animal(nome, especie, raca, idade, porte, estadoSaude, dataResgate, statusAdocao);
        try {
            GestaoAnimais.cadastrarAnimal(administradorAbrigo, animal);
        } catch (UsuarioNaoPossuiAutorizacaoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuFiltrarAnimais(ArrayList<Animal> animaisVisiveis) {
        boolean continuar;
        listaFiltroAtual = animaisVisiveis;
        do {

            continuar = false;
            int option = -1;

            do {
                System.out.println("=========== FILTROS ===========");
                System.out.println("| Selecione uma opção:         |");
                System.out.println("| 1 - Filtrar por ESPÉCIE      |");
                System.out.println("| 2 - Filtrar por PORTE        |");
                System.out.println("| 3 - Filtrar por IDADE        |");
                System.out.println("| 4 - Filtrar por RAÇA         |");
                System.out.println("===============================");
                System.out.print("| => ");
                option = scanner.nextInt();
                scanner.nextLine();
            } while (option < 1 || option > 4);

            ArrayList<Animal> filtroAtual;
            if (option == 1) {
                listaFiltroAtual = menuEspecie();
            } else if (option == 2) {
                listaFiltroAtual = menuPorte();
            } else if (option == 3) {
                listaFiltroAtual = menuIdade();
            } else if (option == 4) {
                listaFiltroAtual = menuRaca();
            }

            if (listaEstaVazia(listaFiltroAtual)) {
                System.out.println("Nenhum animal encontrado com os filtros selecionados.");
            } else {
                continuar = continuarFiltrando();
            }

        } while (continuar);

        GestaoAnimais.imprimirAnimaisLista(listaFiltroAtual);

    }

    public static void menuEditarDadosAnimal(AdministradorAbrigo administradorAbrigo, ArrayList<Animal> animaisVisiveis) {
        Animal animal = selecionarAnimalPorId(animaisVisiveis);
        if (animal == null) return;

        int option = -1;
        do {
            System.out.println("==================== EDITAR DADOS DO ANIMAL ====================");
            System.out.println("| Selecione uma opção:                                         |");
            System.out.println("| 1 - Editar NOME                                              |");
            System.out.println("| 2 - Editar RAÇA                                              |");
            System.out.println("| 3 - Editar PORTE                                             |");
            System.out.println("| 4 - Editar STATUS DE ADOÇÃO                                  |");
            System.out.println("| 0 - Voltar                                                   |");
            System.out.println("================================================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 0 || option > 4);

        if (option == 0) return;

        if (option == 1) {
            System.out.print("Informe o nome do animal: ");
            String nome = scanner.nextLine();
            animal.setNome(nome);
        } else if (option == 2) {
            System.out.print("Informe a raça do animal: ");
            String raca = scanner.nextLine();
            animal.setRaca(raca);
        } else if (option == 3) {
            Porte porte = menuEscolherPorte();
            animal.setPorte(porte);
        } else if (option == 4) {
            StatusAdocao statusAdocao = menuEscolherStatusAdocao();
            animal.setStatusAdocao(statusAdocao);
        }
        System.out.println("Dado atualizado com sucesso!");
    }

    public static void menuRemoverAnimal(AdministradorAbrigo administradorAbrigo, ArrayList<Animal> animaisVisiveis) {
        Animal animal = selecionarAnimalPorId(animaisVisiveis);
        if (animal == null) return;


        int option = -1;
        do {
            System.out.println("==================== REMOVER ANIMAL ====================");
            System.out.println("| Essa ação não poderá ser desfeita, deseja continuar? |");
            System.out.println("| Selecione uma opção:                                 |");
            System.out.println("| 1 - SIM                                              |");
            System.out.println("| 2 - NÃO                                              |");
            System.out.println("========================================================");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option != 1 && option != 2);

        if (option == 1) {
            try {
                GestaoAnimais.removerAnimal(animal);
                System.out.println("Operação concluída com sucesso!");
            } catch (AnimalNaoPodeSerRemovidoException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Operação cancelada com sucesso!");
        }

    }

    public static void menuGerenciarUsuarios(AdministradorAbrigo administradorAbrigo) {
        int option = -1;
        do {
            System.out.println("==================== GERENCIAR USUÁRIOS ====================");
            System.out.println("| Selecione uma opção:                                    |");
            System.out.println("| 1 - Listar todos os usuários disponíveis                |");
            System.out.println("| 2 - Buscar um usuário pelo CPF                         |");
            System.out.println("| 3 - Remover um usuário do sistema                       |");
            System.out.println("| 0 - Voltar                                              |");
            System.out.println("===========================================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 0 || option > 3);

        ArrayList<Animal> animaisVisiveis = administradorAbrigo.getListaAnimaisVisiveis();

        switch (option) {
            case 1:
                GestaoUsuarios.imprimirTodosUsuarios();
                break;
            case 2:
                try {
                    String cpf = lerUmCpf();
                    GestaoUsuarios.imprimirUmUsuario(cpf);
                } catch (UsuarioNaoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                menuRemoverUsuario(administradorAbrigo.getCpf());
                break;
        }
    }

    public static void menuRemoverUsuario(String cpfAdministrador) {
        String cpf = lerUmCpf();

        Usuario usu = null;
        try {
            usu = GestaoUsuarios.imprimirUmUsuario(cpf);
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println(e.getMessage());
            return;
        }

        int option = -1;
        do {
            System.out.println("==================== REMOVER USUÁRIO ====================");
            System.out.println("| Essa ação não poderá ser desfeita, deseja continuar?  |");
            System.out.println("| Selecione uma opção:                                  |");
            System.out.println("| 1 - SIM                                               |");
            System.out.println("| 2 - NÃO                                               |");
            System.out.println("=========================================================");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option != 1 && option != 2);

        if (option == 1) {
            try {
                GestaoUsuarios.removerUsuario(usu, cpfAdministrador);
                System.out.println("Operação concluída com sucesso!");
            } catch (UsuarioNaoPodeSerDeletadoException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Operação cancelada com sucesso!");
        }
    }

    public static void menuGerenciarAnimais(AdministradorAbrigo administradorAbrigo) {
        int option = -1;
        do {
            System.out.println("==================== GERENCIAR ANIMAIS ====================");
            System.out.println("| Selecione uma opção:                                    |");
            System.out.println("| 1 - Cadastrar animal resgatado                          |");
            System.out.println("| 2 - Visualizar todos os animais                         |");
            System.out.println("| 3 - Buscar animais                                      |");
            System.out.println("| 4 - Filtrar animais                                     |");
            System.out.println("| 5 - Editar animal                                       |");
            System.out.println("| 6 - Remover animal                                      |");
            System.out.println("| 0 - Voltar                                              |");
            System.out.println("===========================================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 0 || option > 6);

        ArrayList<Animal> animaisVisiveis = administradorAbrigo.getListaAnimaisVisiveis();

        switch (option) {
            case 1:
                menuCadastrarAnimal(administradorAbrigo);
                break;
            case 2:
                GestaoAnimais.imprimirAnimaisLista(animaisVisiveis);
                break;
            case 3:
                menuBuscarAnimais(animaisVisiveis);
                break;
            case 4:
                menuFiltrarAnimais(animaisVisiveis);
                break;
            case 5:
                menuEditarDadosAnimal(administradorAbrigo, animaisVisiveis);
                break;
            case 6:
                menuRemoverAnimal(administradorAbrigo, animaisVisiveis);
                break;
        }
    }

    public static void menuAtualizarSaudeAnimal(ArrayList<Animal> animaisVisiveis, Veterinario veterinario) {

        Animal animal = selecionarAnimalPorId(animaisVisiveis);
        if (animal == null) return;

        int option = -1;
        do {
            System.out.println("=============== ATUALIZAR ESTADO DE SAÚDE ===============");
            System.out.println("| Selecione uma opção:                                  |");
            System.out.println("| 1 - Atualizar para 'SAÚDAVEL'                         |");
            System.out.println("| 2 - Atualizar para 'EM TRATAMENTO'                    |");
            System.out.println("| 3 - Atualizar para 'EM TRATAMENTO CRÍTICO'            |");
            System.out.println("=========================================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 1 || option > 3);

        switch (option) {
            case 1:
                GestaoRegistrosMedicos.atualizarEstadoSaudeAnimal(animal, EstadoSaude.SAUDAVEL, veterinario);
                break;
            case 2:
                GestaoRegistrosMedicos.atualizarEstadoSaudeAnimal(animal, EstadoSaude.EM_TRATAMENTO, veterinario);
                break;
            case 3:
                GestaoRegistrosMedicos.atualizarEstadoSaudeAnimal(animal, EstadoSaude.EM_TRATAMENTO_CRITICO, veterinario);
                break;
        }
    }

    public static void menuSolicitarAdocao(ArrayList<Animal> animaisVisiveis, Adotante adotante) {
        int option = -1;
        Animal animal;
        do {
            animal = selecionarAnimalPorId(animaisVisiveis);
            if (animal == null) return;
            ArrayList<Animal> lista = new ArrayList<>();
            lista.add(animal);
            GestaoAnimais.imprimirAnimaisLista(lista);
            do {
                System.out.println("====================== CONFIRMAR ESCOLHA DE ANIMAL ======================");
                System.out.println("| Deseja prosseguir com a solicitação de adoção do animal encontrado?   |");
                System.out.println("| Selecione uma opção:                                                  |");
                System.out.println("| 1 - SIM                                                               |");
                System.out.println("| 2 - NÃO                                                               |");
                System.out.println("=========================================================================");
                option = scanner.nextInt();
                scanner.nextLine();
            } while (option != 1 && option != 2);

        } while (option != 1);

        System.out.println("Data da solicitação: ");
        Data dataSolicitacao = lerData();
        try {
            GestaoAdocoes.solicitarAdocao(adotante, animal, dataSolicitacao);
            System.out.println("Solicitação de adoção registrada com sucesso!");
        } catch (AnimalIndisponivelException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuAvaliarSolicitacaoPorId(AdministradorAbrigo administradorAbrigo) {
        int option = -1;
        System.out.print("Informe o ID da solicitação: ");
        String id = scanner.nextLine();
        SolicitacaoAdocao solicitacaoAdocao = GestaoAdocoes.getSolicitacaoPorId(id, administradorAbrigo);
        System.out.println(solicitacaoAdocao.getResumo());
        do {
            System.out.println("==================== AVALIAR SOLICITAÇÃO =====================");
            System.out.println("| Selecione uma opção:                                       |");
            System.out.println("| 1 - APROVAR                                                |");
            System.out.println("| 2 - REJEITAR                                               |");
            System.out.println("==============================================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();

        } while (option != 1 && option != 2);

        if (option == 1) {
            GestaoAdocoes.avaliarSolicitacao(administradorAbrigo, id, true);
        } else {
            GestaoAdocoes.avaliarSolicitacao(administradorAbrigo, id, false);
        }
        System.out.println("Solicitação avaliada com sucesso!");
    }

    public static void menuRegistrarDevolucao(AdministradorAbrigo administradorAbrigo) {
        int option = -1;
        System.out.print("Informe o ID da solicitação: ");
        String id = scanner.nextLine();
        SolicitacaoAdocao solicitacaoAdocao = GestaoAdocoes.getSolicitacaoPorId(id, administradorAbrigo);
        System.out.println(solicitacaoAdocao.getResumo());
        System.out.print("Motivo da devolução: ");
        String observacao = scanner.nextLine();
        do {
            System.out.println("==================== REGISTRAR DEVOLUÇÃO =====================");
            System.out.println("| Selecione uma opção:                                       |");
            System.out.println("| 1 - SIM                                                    |");
            System.out.println("| 2 - NÃO                                                    |");
            System.out.println("==============================================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();

        } while (option != 1 && option != 2);

        if (option == 1) {
            GestaoAdocoes.registrarDevolucao(administradorAbrigo, solicitacaoAdocao, observacao);
            System.out.println("Devolução registrada com sucesso!");
        } else {
            System.out.println("Operação cancelada!");
        }
    }

    public static void menuInscreverAtividade(Voluntario voluntario) {
        try {
            GestaoAtividades.imprimirTodasAtividades();
            System.out.print("Informe o ID da atividade: ");
            String idAtividade = scanner.nextLine();
            GestaoAtividades.inscreverVoluntarioAtividade(voluntario, idAtividade);
            System.out.println("Inscrição realizada com sucesso!");
        } catch (NenhumaAtividadeCadastradaException e) {
            System.out.println(e.getMessage());
        } catch (VoluntarioJaInscritoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuAgendarAtividade(AdministradorAbrigo administradorAbrigo) {
        int option = -1;
        do {
            System.out.println("====================== AGENDAR ATIVIDADE ======================");
            System.out.println("| Informe o tipo da atividade:                                |");
            System.out.println("| Selecione uma opção:                                        |");
            System.out.println("| 1 - Multirão de Limpeza                                     |");
            System.out.println("| 2 - Campanha de Vacinação                                   |");
            System.out.println("| 3 - Feira de Adoção                                         |");
            System.out.println("| 4 - Evento Beneficente                                      |");
            System.out.println("================================================================");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 1 || option > 4);

        System.out.print("Informe o nome do evento: ");
        String nome = scanner.nextLine();
        System.out.println("Informe a data do evento: ");
        Data dataEvento = lerData();
        TipoAtividade tipo;

        if (option == 1) tipo = TipoAtividade.MUTIRAO_LIMPEZA;
        else if (option == 2) tipo = TipoAtividade.CAMPANHA_VACINACAO;
        else if (option == 3) tipo = TipoAtividade.FEIRA_ADOCAO;
        else tipo = TipoAtividade.EVENTO_BENEFICENTE;

        GestaoAtividades.cadastrarAtividade(administradorAbrigo, nome, tipo, dataEvento);
        System.out.printf("Atividade agendada com sucesso para o dia %s.\n", dataEvento);
    }

    public static void menuRegistrarAtendimentoMedico(Veterinario veterinario) {
        int option = -1;
        do {
            System.out.println("=============== ATENDIMENTO MÉDICO ===============");
            System.out.println("| Selecione uma opção:                           |");
            System.out.println("| 1 - Registrar uma VACINA                       |");
            System.out.println("| 2 - Registrar uma CONSULTA MÉDICA              |");
            System.out.println("| 3 - Registrar uma MEDICAÇÃO                    |");
            System.out.println("| 4 - Registrar uma CIRURGIA                     |");
            System.out.println("==================================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 1 || option > 4);

        Animal animal = selecionarAnimalPorId(veterinario.getListaAnimaisVisiveis());
        if (animal == null) return;

        switch (option) {
            case 1:
                try {
                    System.out.println("Data de Aplicação: ");
                    Data dataRealizacao = lerData();
                    System.out.println("Data de Vencimento: ");
                    Data dataVencimento = lerData();
                    System.out.print("Descrição da vacina: ");
                    String descricao = scanner.nextLine();
                    GestaoRegistrosMedicos.registrarVacina(animal, veterinario, dataRealizacao, veterinario, descricao, dataVencimento);
                    System.out.println("Registrado com sucesso!");
                } catch (VacinaDuplicadaException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro. Não foi possível concluir a operação.");
                }
                break;
            case 2:
                try {
                    System.out.println("Data da Consulta: ");
                    Data dataConsulta = lerData();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Diagnóstico: ");
                    String diagnostico = scanner.nextLine();
                    GestaoRegistrosMedicos.registrarConsulta(animal, veterinario, dataConsulta, veterinario, descricao, diagnostico);
                    System.out.println("Registrado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro. Não foi possível concluir a operação.");
                }
                break;
            case 3:
                try {
                    System.out.println("Data da Medicação: ");
                    Data dataMedicacao = lerData();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Dosagem: ");
                    String dosagem = scanner.nextLine();
                    System.out.print("Duração do tratamento (em dias): ");
                    int duracao = scanner.nextInt();
                    scanner.nextLine();
                    GestaoRegistrosMedicos.registrarMedicamento(animal, veterinario, dataMedicacao, veterinario, descricao, dosagem, duracao);
                    System.out.println("Registrado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro. Não foi possível concluir a operação.");
                }
                break;

            case 4:
                try {
                    System.out.println("Data da Cirurgia: ");
                    Data dataCirurgia = lerData();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Cuidados no pós-operatório: ");
                    String cuidados = scanner.nextLine();
                    GestaoRegistrosMedicos.registrarCirurgia(animal, veterinario, dataCirurgia, veterinario, descricao, cuidados);
                    System.out.println("Registrado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro. Não foi possível concluir a operação.");
                }
                break;
        }
    }

    public static void menuGerenciarAdocoes(AdministradorAbrigo administradorAbrigo) {
        int option = -1;
        do {

            do {
                System.out.println("==================== GERENCIAR ADOÇÕES ====================");
                System.out.println("| Selecione uma opção:                                    |");
                System.out.println("| 1 - Listar todo o histórico de solicitações de adoção   |");
                System.out.println("| 2 - Listar as solicitações de adoção pendentes          |");
                System.out.println("| 3 - Avaliar uma solicitação                             |");
                System.out.println("| 4 - Registrar a devolução de um animal                  |");
                System.out.println("| 0 - Voltar                                              |");
                System.out.println("===========================================================");
                System.out.print("| => ");
                option = scanner.nextInt();
                scanner.nextLine();
            } while (option < 0 || option > 4);

            try {
                switch (option) {
                    case 1:
                        GestaoAdocoes.imprimirSolicitacoes(administradorAbrigo);
                        break;
                    case 2:
                        ArrayList<SolicitacaoAdocao> pendentes = GestaoAdocoes.getSolicitacoesPendentes(administradorAbrigo);
                        for (SolicitacaoAdocao solicitacao : pendentes) {
                            System.out.println(solicitacao.getResumo());
                        }
                        break;
                    case 3:
                        menuAvaliarSolicitacaoPorId(administradorAbrigo);
                        break;
                    case 4:
                        menuRegistrarDevolucao(administradorAbrigo);
                        break;
                }
            } catch (UsuarioNaoPossuiAutorizacaoException e) {
                System.out.println(e.getMessage());
            } catch (SolicitacaoJaAvaliadaException e) {
                System.out.println(e.getMessage());
            } catch (SolicitacaoNaoEncontradaException e) {
                System.out.println(e.getMessage());
            } catch (NaoHaSolicitacoesPendentesException e) {
                System.out.println(e.getMessage());
            } catch (SolicitacaoNaoFoiAprovadaException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Ocorreu um erro desconhecido.");
            }

        } while ((option != 0));
    }

    public static int menuEscolhaUsuario() {
        int option = -1;
        do {
            System.out.println("=============== CADASTRO ===============");
            System.out.println("| Selecione uma opção:                 |");
            System.out.println("| 1 - Cadastrar como VOLUNTÁRIO        |");
            System.out.println("| 2 - Cadastrar como VETERINÁRIO       |");
            System.out.println("| 3 - Cadastrar como ADMINISTRADOR     |");
            System.out.println("| 4 - Cadastrar como ADOTANTE          |");
            System.out.println("========================================");
            System.out.print("| => ");
            option = scanner.nextInt();
            scanner.nextLine();
        } while (option < 1 || option > 4);
        return option;
    }

    public static void menuCadastrarUsuario() {
        int escolhaUsuario = menuEscolhaUsuario();
        try {
            System.out.print("Informe seu nome: ");
            String nome = scanner.nextLine();
            System.out.print("Informe seu CPF: ");
            String cpf = scanner.nextLine();
            GestaoUsuarios.cpfValido(cpf);
            System.out.print("Informe seu email:");
            String email = scanner.nextLine();
            System.out.println("Data de Nascimento: ");
            Data dataNascimento = lerData();
            System.out.println("Crie uma senha: ");
            String senha = scanner.nextLine();
            GestaoUsuarios.senhaValida(senha);

            switch (escolhaUsuario) {
                case 1:
                    Voluntario voluntario = new Voluntario(nome, cpf, email, dataNascimento, senha);
                    GestaoUsuarios.cadastrarUsuario(voluntario);
                    break;
                case 2:
                    Veterinario veterinario = new Veterinario(nome, cpf, email, dataNascimento, senha);
                    GestaoUsuarios.cadastrarUsuario(veterinario);
                    break;
                case 3:
                    AdministradorAbrigo administradorAbrigo = new AdministradorAbrigo(nome, cpf, email, dataNascimento, senha);
                    GestaoUsuarios.cadastrarUsuario(administradorAbrigo);
                    break;
                case 4:
                    Adotante adotante = new Adotante(nome, cpf, email, dataNascimento, senha);
                    GestaoUsuarios.cadastrarUsuario(adotante);
                    break;
            }
            System.out.println("Usuário cadastrado com sucessso!\n");
        } catch (CPFInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (UsuarioJaCadastradoException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (SenhaInvalidaException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void menuEmitirRelatorios(AdministradorAbrigo administradorAbrigo) {
        int option = -1;
        do {

            do {
                System.out.println("==================== EMITIR RELATÓRIOS ====================");
                System.out.println("| Selecione uma opção:                                    |");
                System.out.println("| 1 - Quantidade de animais resgatados                    |");
                System.out.println("| 2 - Quantidade de adoções realizadas                    |");
                System.out.println("| 3 - Espécies mais resgatadas                            |");
                System.out.println("| 4 - Taxa de adoção mensal                               |");
                System.out.println("| 5 - Número de voluntários ativos                        |");
                System.out.println("| 6 - Relatório de animais em tratamento                  |");
                System.out.println("| 0 - Voltar                                              |");
                System.out.println("===========================================================");
                System.out.print("| => ");
                option = scanner.nextInt();
                scanner.nextLine();
            } while (option < 0 || option > 6);

            switch (option) {
                case 1:
                    System.out.println(GestaoAnimais.emitirRelatorioTotalAnimaisResgatados());
                    break;
                case 2:
                    System.out.println(GestaoAdocoes.emitirRelatorioQuantidadeAdocoes());
                    break;
                case 3:
                    System.out.println(GestaoAnimais.emitirRelatorioEspeciesResgatadas());
                    break;     
                case 4:
                    System.out.println("Informe o mês: (1 - 12)");
                    int mes = scanner.nextInt();
                    System.out.println("Informe o ano: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();
                    if ((mes < 1 || mes > 12) || (ano < 1926 || ano > 2050)) {
                        System.out.println("Data inválida.");
                        break;
                    }
                    System.out.println(GestaoAdocoes.emitirRelatorioTaxaAdocaoMensal(mes, ano));
                    break;
                case 5:
                    System.out.println(GestaoUsuarios.emitirRelatorioVoluntariosAtivos());
                    break;   
                case 6:
                    System.out.println(GestaoAnimais.emitirRelatorioSaudeAnimais());
                    break;            
            }
        } while (option != 0);
    }


    public static Usuario menuLoginUsuario() {

        System.out.println("=============== LOGIN ===============");
        System.out.print("| CPF: ");
        String cpf = scanner.nextLine();
        System.out.println("| SENHA: ");
        String senha = scanner.nextLine();
        System.out.println("=====================================\n");
        try {
            Usuario usuarioLogado = GestaoUsuarios.autenticarUsuario(cpf, senha);
            System.out.println("Usuário autenticado com sucesso!");
            return usuarioLogado;
        } catch (CredenciaisInvalidasException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void menuInicialUsuario() {
        System.out.println("Seja bem vindo ao Sistema de Gerenciamento de Abrigos e Resgates de Animais!\n" +
                "Uma plataforma que facilita o controle dos resgates, acompanhamento da saúde dos animais,\n" +
                " gerenciamento de adoções e comunicação entre os responsáveis pelos abrigos e os voluntários.");
        System.out.println("Por gentileza, pedimos que você informe a data de hoje.");
        try {
            DATA_ATUAL = lerData();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        int option = -1;
        boolean encerrar = false;
        do {
            do {
                System.out.println("============ INÍCIO ============");
                System.out.println("| Selecione uma opção:         |");
                System.out.println("| 1 - Cadastrar usuário        |");
                System.out.println("| 2 - Fazer login              |");
                System.out.println("| 0 - Sair do sistema          |");
                System.out.println("================================");
                System.out.print("| => ");
                option = scanner.nextInt();
                scanner.nextLine();
            } while (option < 0 || option > 2);

            switch (option) {
                case 1:
                    menuCadastrarUsuario();
                    break;
                case 2:
                    Usuario usuarioLogado = menuLoginUsuario();
                    if (usuarioLogado != null) {
                        usuarioLogado.exibirMenu();
                    }
                    break;
                case 0:
                    encerrar = true;
                    break;
            }

        } while (encerrar == false);
    }

    public static void exibirMenuVoluntario(Voluntario voluntario) {
        int option = -1;
        do {

            do {
                System.out.println("==================== MENU PRINCIPAL ====================");
                System.out.println("| Selecione uma opção:                                 |");
                System.out.println("| 1 - Visualizar animais disponíveis para adoção       |");
                System.out.println("| 2 - Buscar animais disponíveis                       |");
                System.out.println("| 3 - Filtrar animais disponíveis                      |");
                System.out.println("| 4 - Candidatar-se para atividades de apoio           |");
                System.out.println("| 5 - Consultar histórico de participações na ONG      |");
                System.out.println("| 6 - Acompanhar animais sob sua responsabilidade      |");
                System.out.println("| 7 - Visualizar notificações                          |");
                System.out.println("| 0 - Logout                                           |");
                System.out.println("========================================================");
                System.out.print("| => ");
                option = scanner.nextInt();
                scanner.nextLine();
            } while (option < 0 || option > 7);

            ArrayList<Animal> animaisVisiveis = voluntario.getListaAnimaisVisiveis();
            switch (option) {
                case 1:
                    GestaoAnimais.imprimirAnimaisLista(animaisVisiveis);
                    break;
                case 2:
                    menuBuscarAnimais(animaisVisiveis);
                    break;
                case 3:
                    menuFiltrarAnimais(animaisVisiveis);
                    break;
                case 4:
                    menuInscreverAtividade(voluntario);
                    break;
                case 5:
                    try {
                        voluntario.historicoVazio();
                        System.out.println("Histórico de participações: ");
                        ArrayList<String> historico = voluntario.getHistoricoParticipacoes();
                        for (String participacao : historico) {
                            System.out.println(participacao);
                        }
                    } catch (HistoricoParticipacoesVazioException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    menuAcompanharAnimal(voluntario);
                    break;
                case 7:
                    GestaoUsuarios.imprimirNotificacoesUsuario(voluntario);
                    break;
            }

        } while (option != 0);
    }

    public static void exibirMenuVeterinario(Veterinario veterinario) {
        int option = -1;
        do {
            do {
                System.out.println("==================== MENU PRINCIPAL ====================");
                System.out.println("| Selecione uma opção:                                 |");
                System.out.println("| 1 - Visualizar animais                               |");
                System.out.println("| 2 - Buscar animais                                   |");
                System.out.println("| 3 - Filtrar animais                                  |");
                System.out.println("| 4 - Registrar atendimento médico                     |");
                System.out.println("| 5 - Atualizar estado de saúde do animal              |");
                System.out.println("| 6 - Consultar histórico medico de um animal          |");
                System.out.println("| 7 - Emitir relatório de saúde                        |");
                System.out.println("| 8 - Visualizar notificações                          |");
                System.out.println("| 0 - Logout                                           |");
                System.out.println("========================================================");
                System.out.print("| => ");
                option = scanner.nextInt();
                scanner.nextLine();
            } while (option < 0 || option > 8);

            ArrayList<Animal> animaisVisiveis = veterinario.getListaAnimaisVisiveis();

            switch (option) {
                case 1:
                    GestaoAnimais.imprimirAnimaisLista(animaisVisiveis);
                    break;
                case 2:
                    menuBuscarAnimais(animaisVisiveis);
                    break;
                case 3:
                    menuFiltrarAnimais(animaisVisiveis);
                    break;
                case 4:
                    menuRegistrarAtendimentoMedico(veterinario);
                    break;
                case 5:
                    menuAtualizarSaudeAnimal(animaisVisiveis, veterinario);
                    break;
                case 6:
                    consultarHistoricoMedicoAnimal(animaisVisiveis);
                    break;
                case 7:
                    System.out.println(GestaoAnimais.emitirRelatorioSaudeAnimais());
                    break;
                case 8:
                    GestaoUsuarios.imprimirNotificacoesUsuario(veterinario);
                    break;

            }
        } while (option != 0);


    }

    public static void exibirMenuAdotante(Adotante adotante) {
        int option = -1;
        do {
            do {
                System.out.println("==================== MENU PRINCIPAL ====================");
                System.out.println("| Selecione uma opção:                                 |");
                System.out.println("| 1 - Visualizar animais disponíveis                   |");
                System.out.println("| 2 - Buscar animais disponíveis                       |");
                System.out.println("| 3 - Filtrar animais disponíveis                      |");
                System.out.println("| 4 - Solicitar adoção                                 |");
                System.out.println("| 5 - Acompanhar minhas solicitações                   |");
                System.out.println("| 6 - Visualizar notificações                          |");
                System.out.println("| 0 - Logout                                           |");
                System.out.println("========================================================");
                System.out.print("| => ");
                option = scanner.nextInt();
                scanner.nextLine();
            } while (option < 0 || option > 6);

            ArrayList<Animal> animaisVisiveis = adotante.getListaAnimaisVisiveis();

            switch (option) {
                case 1:
                    GestaoAnimais.imprimirAnimaisLista(animaisVisiveis);
                    break;
                case 2:
                    menuBuscarAnimais(animaisVisiveis);
                    break;
                case 3:
                    menuFiltrarAnimais(animaisVisiveis);
                    break;
                case 4:
                    menuSolicitarAdocao(animaisVisiveis, adotante);
                    break;
                case 5:
                    GestaoAdocoes.verificarAdocoesCpf(adotante);
                    break;
                case 6:
                    GestaoUsuarios.imprimirNotificacoesUsuario(adotante);
                    break;
            }

        } while (option != 0);

    }

    public static void exibirMenuAdministradorAbrigo(AdministradorAbrigo administradorAbrigo) {
        int option = -1;
        do {
            do {
                System.out.println("==================== MENU PRINCIPAL ====================");
                System.out.println("| Selecione uma opção:                                 |");
                System.out.println("| 1 - Gerenciar animais                                |");
                System.out.println("| 2 - Gerenciar adoções                                |");
                System.out.println("| 3 - Gerenciar usuários                               |");
                System.out.println("| 4 - Agendar atividade                                |");
                System.out.println("| 5 - Emitir relatórios e estatísticas                 |");
                System.out.println("| 6 - Enviar aviso geral                               |");
                System.out.println("| 7 - Visualizar notificações                          |");
                System.out.println("| 0 - Logout                                           |");
                System.out.println("========================================================");
                System.out.print("| => ");
                option = scanner.nextInt();
                scanner.nextLine();
            } while (option < 0 || option > 7);

            ArrayList<Animal> animaisVisiveis = administradorAbrigo.getListaAnimaisVisiveis();

            switch (option) {
                case 1:
                    menuGerenciarAnimais(administradorAbrigo);
                    break;
                case 2:
                    menuGerenciarAdocoes(administradorAbrigo);
                    break;
                case 3:
                    menuGerenciarUsuarios(administradorAbrigo);
                    break;
                case 4:
                    menuAgendarAtividade(administradorAbrigo);
                    break;
                case 5:
                    menuEmitirRelatorios(administradorAbrigo);
                    break;
                case 6:
                    System.out.print("Cadastre um aviso geral: ");
                    String aviso = scanner.nextLine();
                    administradorAbrigo.emitiAvisoGeral(aviso);
                    break;
                case 7:
                    GestaoUsuarios.imprimirNotificacoesUsuario(administradorAbrigo);
                    break;
            }


        } while (option != 0);
    }

}
