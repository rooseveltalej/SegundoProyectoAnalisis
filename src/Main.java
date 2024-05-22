//Fecha de creación: 10/05/2024
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Item[] items = {
            new Item("Item1", 2, 3),
            new Item("Item2", 3, 4),
            new Item("Item3", 4, 5),
            new Item("Item4", 5, 7)
        };

        Mochila mochila = new Mochila(6, items);

        // Resolver usando programación dinámica
        Mochila resultadoDP = Dinamico.resolverMochilaDP(mochila);
        System.out.println("Resultado usando Programación Dinámica: " + resultadoDP);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoDP.getItems()).map(Item::getNombre).toList());

        // Resolver usando algoritmo genético
        Mochila resultadoGA = Genetico.resolverMochilaGA(mochila);
        System.out.println("Resultado usando Algoritmo Genético: " + resultadoGA);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoGA.getItems()).map(Item::getNombre).toList());
    }
} //Hola mundo


