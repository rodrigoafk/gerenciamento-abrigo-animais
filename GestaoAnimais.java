
import java.util.ArrayList;



public class GestaoAnimais {


    private static final ArrayList<Animal> animais = new ArrayList<>();

    public static ArrayList<Animal> getTodosAnimais() {
        ArrayList<Animal> copiaLista = new ArrayList<>();
        for (Animal a : animais) {
            copiaLista.add(a);
        }
        return copiaLista;
    }




    public static void removerAnimal(Animal animal) {
        if (animal.getStatusAdocao() == StatusAdocao.ADOTADO) {
            throw new AnimalNaoPodeSerRemovidoException("Erro: Animais que já foram adotados não podem ser deletados do sistema");
        }

        for (Animal a : animais) {
            if (a.getId().equals(animal.getId())) {
                animais.remove(a);
                return;
            }
        }
    }

    public static String emitirRelatorioSaudeAnimais() {
        int saudaveis = 0, emTratamento = 0, emTratamentoCritico = 0;
        for (Animal animal : animais) {
            EstadoSaude estado = animal.getEstadoSaude();
            if (estado == EstadoSaude.SAUDAVEL) saudaveis++;
            else if (estado == EstadoSaude.EM_TRATAMENTO) emTratamento++;
            else if (estado == EstadoSaude.EM_TRATAMENTO_CRITICO) emTratamentoCritico++;
        }
        return String.format("Animais saudáveis: %d \nAnimais em tratamento: %d \nAnimais em tratamento crítico: %d\n", saudaveis, emTratamento, emTratamentoCritico);
    }

    public static void imprimirAnimaisLista(ArrayList<Animal> lista) {
        int animalAtual = 0;
        for (Animal a : lista) {
            animalAtual++;
            System.out.printf("============== DADOS DO %dº ANIMAL ==============\n", animalAtual);
            System.out.printf("ID: %s\n", a.getId());
            System.out.printf("NOME: %s \n", a.getNome());
            System.out.printf("ESPÉCIE: %s \n", a.getEspecie());
            System.out.printf("RAÇA: %s\n", a.getRaca());
            if (a.getIdade() == 1) {
                System.out.printf("IDADE: %d mês\n", a.getIdade());
            } else if (a.getIdade() < Animal.MESES_DO_ANO) {
                System.out.printf("IDADE: %d meses\n", a.getIdade());
            } else {
                System.out.printf("IDADE: %d anos\n", a.getIdadeEmAnos());
            }
            System.out.printf("PORTE: %s\n", a.getPorte());
            System.out.printf("ESTADO DE SAÚDE: %s\n", a.getEstadoSaude());
            System.out.printf("DATA DE RESGATE: %s\n", a.getDataResgate());
            System.out.println("=================================================");
        }
    }

    public static void cadastrarAnimal(Usuario usuario, Animal animal) {
        if (usuario instanceof AdministradorAbrigo) {
            animais.add(animal);
            return;
        }

        throw new UsuarioNaoPossuiAutorizacaoException("Erro: Somente um administrador do abrigo pode cadastrar animais.");

    }

    public static ArrayList<Animal> animaisDisponiveis() {
        ArrayList<Animal> animaisDisponiveis = new ArrayList<>();
        for (Animal animal : animais) {
            if (animal.getStatusAdocao() == StatusAdocao.ADOTADO || animal.getEstadoSaude() == EstadoSaude.EM_TRATAMENTO_CRITICO) {
                continue;
            }
            animaisDisponiveis.add(animal);
        }
        return animaisDisponiveis;
    }

    public static Animal buscarAnimalId(String id, ArrayList<Animal> animaisVisiveis) {
        for (Animal a : animaisVisiveis) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        throw new IdNaoLocalizadoException("Não há nenhum animal cadastrado com o ID informado");
    }

    public static ArrayList<Animal> buscarAnimaisNome(String nome, ArrayList<Animal> animaisVisiveis) {
        ArrayList<Animal> animaisNome = new ArrayList<>();
        boolean localizado = false;
        for (Animal a : animaisVisiveis) {
            if (a.getNome().toUpperCase().equals(nome.toUpperCase())) {
                animaisNome.add(a);
                localizado = true;
            }
        }
        if (localizado) return animaisNome;
        throw new NomeNaoLocalizadoException("Não há nenhum animal cadastrado com o nome informado");
    }

    public static ArrayList<Animal> filtrarAnimaisPorRaca(String racaBuscada, ArrayList<Animal> listaFiltroAtual) {
        ArrayList<Animal> animaisDessaRaca = new ArrayList<>();
        for (Animal a : listaFiltroAtual) {
            if (a.getRaca().toUpperCase().equals(racaBuscada.toUpperCase())) {
                animaisDessaRaca.add(a);
            }
        }
        return animaisDessaRaca;
    }

    public static ArrayList<Animal> filtrarAnimaisPorIdade(int idadeMin, int idadeMax, ArrayList<Animal> listaFiltroAtual) {
        if (idadeMin > idadeMax || idadeMin < 0) {
            throw new BuscaInvalidaException("A faixa etária buscada é inválida");
        }
        ArrayList<Animal> animaisFaixaEtaria = new ArrayList<>();
        for (Animal a : listaFiltroAtual) {
            if (a.getIdadeEmAnos() >= idadeMin && a.getIdadeEmAnos() <= idadeMax) {
                animaisFaixaEtaria.add(a);
            }
        }
        return animaisFaixaEtaria;
    }

    public static ArrayList<Animal> filtrarAnimaisPorEspecie(Especie especie, ArrayList<Animal> listaFiltroAtual) {
        ArrayList<Animal> animaisDaEspecie = new ArrayList<>();
        for (Animal a : listaFiltroAtual) {
            if (a.getEspecie() == especie) animaisDaEspecie.add(a);
        }
        return animaisDaEspecie;
    }

    public static ArrayList<Animal> filtrarAnimaisPorPorte(Porte porte, ArrayList<Animal> listaFiltroAtual) {
        ArrayList<Animal> animaisPorte = new ArrayList<>();
        for (Animal a : listaFiltroAtual) {
            if (a.getPorte() == porte) animaisPorte.add(a);
        }
        return animaisPorte;
    }



}
