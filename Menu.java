import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Animal> listaFiltroAtual = new ArrayList<>();

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
        int option = 0;
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
        int option = 0;

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

    public static Especie menuEscolherEspecie() {
        int option = 0;
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
        int option = 0;
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
        else return (int) idade / Animal.MESES_DO_ANO;
    }

    public static Porte menuEscolherPorte() {
        int option = 0;
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
        int option = 0;
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
        int option = 0;
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
            int option = 0;

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

    public static void menuGerenciarAnimais(AdministradorAbrigo administradorAbrigo) {
        int option = 0;
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

        }
    }

    public static void menuAtualizarSaudeAnimal(ArrayList<Animal> animaisVisiveis, Veterinario veterinario) {

        Animal animal = selecionarAnimalPorId(animaisVisiveis);
        if (animal == null) return;

        int option = 0;
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



    public static void menuRegistrarAtendimentoMedico(Veterinario veterinario) {
        int option = 0;
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

    public static int menuEscolhaUsuario() {
        int option = 0;
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
        int option = 0;
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
        int option = 0;
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
                    // a implementar
                    break;
                case 5:
                    voluntario.historicoVazio();
                    System.out.println("Histórico de participações: ");
                    ArrayList<String> historico = voluntario.getHistoricoParticipacoes();
                    for (String participacao : historico) {
                        System.out.println(participacao);
                    }
                    break;
            }

        } while (option != 0);
    }

    public static void exibirMenuVeterinario(Veterinario veterinario) {
        int option = 0;
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
                    // notificações
                    break;

            }
        } while (option != 0);


    }

    public static void exibirMenuAdministradorAbrigo(AdministradorAbrigo administradorAbrigo) {
        int option = 0;
        do {
            do {
                System.out.println("==================== MENU PRINCIPAL ====================");
                System.out.println("| Selecione uma opção:                                 |");
                System.out.println("| 1 - Gerenciar animais                                |");
                System.out.println("| 2 - Gerenciar adoções                                |");
                System.out.println("| 3 - Gerenciar usuários                               |");
                System.out.println("| 4 - Agendar atividade                                |");
                System.out.println("| 5 - Emitir relatórios e estáticas                    |");
                System.out.println("| 6 - Enviar aviso geral                               |");
                System.out.println("| 7 - Visualizar notificações                          |");
                System.out.println("| 0 - Logout                                           |");
                System.out.println("========================================================");
                System.out.print("| => ");
                option = scanner.nextInt();
                scanner.nextLine();
            } while (option < 0 || option > 7);

            ArrayList<Animal> animaisVisiveis = administradorAbrigo.getListaAnimaisVisiveis();


        } while (option != 0);
    }

}
