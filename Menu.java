import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Animal> listaFiltroAtual = new ArrayList<>();

    public static boolean listaEstaVazia(ArrayList<Animal> lista) {
        if (lista == null || lista.isEmpty()) {
            return true;
        }
        return false;
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

    public static void menuBuscarAnimais() {
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
                listaAnimalId.add(GestaoAnimais.buscarAnimalId(idBuscado));
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
                ArrayList<Animal> animaisNome = GestaoAnimais.buscarAnimaisNome(nomeBuscado);
                GestaoAnimais.imprimirAnimaisLista(animaisNome);
            } catch (NomeNaoLocalizadoException e) {
                System.out.println(e.getMessage());
                System.out.println("================================");
            }
        }
    }

    public static void menuFiltrarAnimais() {
        boolean continuar;
        listaFiltroAtual = GestaoAnimais.getTodosAnimais();
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


}
