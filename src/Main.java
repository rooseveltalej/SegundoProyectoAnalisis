//Fecha de creación: 10/05/2024
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Ejercicio de los 5 objetos iniciales 
        Item[] items = {
            new Item("Bloqueador", 3, 9),
            new Item("Ropa", 5, 10),
            new Item("Sombrilla", 2, 7),
            new Item("Desodorante", 3, 9),
            new Item("Cepillo de dientes",2,10)
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
        int peso;
        int valor;
        for (int i = 0; i < 10; i++) {
            peso = (int)(Math.random() * 50 + 1);
            valor = (int)(Math.random() * 50 + 1);
            items2 = Arrays.copyOf(items2, items2.length + 1);
            items2[items2.length - 1] = new Item("Item" + (i + 1), peso, valor);
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
            //Prueba 10 items. Pero necesito saber si lo hago con los valores generados de esa forma o de otra manera
    }
} //Hola mundo


