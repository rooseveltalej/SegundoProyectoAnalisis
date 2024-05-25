//Fecha de creación: 10/05/2024
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Item[] items = {
            new Item("Item1", 2, 3),
            new Item("Item2", 3, 4),
            new Item("Item3", 4, 5),
            new Item("Item4", 5, 7),
            new Item("Item5",6,15)
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
             //Prueba 5 items
        

        Item[] items2 = {};
        for (int i = 0; i < 10; i++) {
            items2 = Arrays.copyOf(items2, items2.length + 1);
            items2[items2.length - 1] = new Item("Item" + (i + 1), i + 1, i + 1);
        }

        Mochila mochila2 = new Mochila(10, items2);

        //Resolver usando programación dinámica
        Mochila resultadoDP2 = Dinamico.resolverMochilaDP(mochila2);
        System.out.println("Resultado usando Programación Dinámica: " + resultadoDP2);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoDP2.getItems()).map(Item::getNombre).toList());

        //Resolver usando algoritmo genético
        Mochila resultadoGA2 = Genetico.resolverMochilaGA(mochila2);
        System.out.println("Resultado usando Algoritmo Genético: " + resultadoGA2);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoGA2.getItems()).map(Item::getNombre).toList());
            
    }
} //Hola mundo


