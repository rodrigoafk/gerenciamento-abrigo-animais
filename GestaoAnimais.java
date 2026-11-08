import java.sql.Array;
import java.util.ArrayList;

public class GestaoAnimais {
    private static ArrayList<Animal> animais = new ArrayList<>();

    public static void addAnimal(Animal animal) {
        animais.add(animal);
    }
}
