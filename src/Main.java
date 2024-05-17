//Fecha de creación: 10/05/2024
public class Main {
    public static void main(String[] args) {
        Item[] items = {
            new Item(2, 3),
            new Item(3, 4),
            new Item(4, 5),
            new Item(5, 8)
        };

        Mochila mochila = new Mochila(5, items);

        // Resolver usando programación dinámica
        Mochila resultadoDP = Dinamico.resolverMochilaDP(mochila);
        System.out.println("Resultado usando Programación Dinámica: " + resultadoDP);

        // Resolver usando algoritmo genético
        Mochila resultadoGA = Genetico.resolverMochilaGA(mochila);
        System.out.println("Resultado usando Algoritmo Genético: " + resultadoGA);
    }
}

