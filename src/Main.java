//Fecha de creación: 10/05/2024
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // esperar entrada de usuario
            System.out.println("Seleccione una opción: ");
            System.out.println("1. Mochila con 5 items");
            System.out.println("2. Mochila con 10 items");
            System.out.println("3. Mochila con 20 items");
            System.out.println("4. Mochila con 30 items");
            System.out.println("5. Mochila con 40 items");
            System.out.println("6. Mochila con 50 items");
            System.out.println("7. Salir");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Mochila1();
                    break;
                case 2:
                    Mochila2();
                    break;
                case 3:
                    Mochila3();
                    break;
                case 4:
                    Mochila4();
                    break;
                case 5:
                    Mochila5();
                    break;
                case 6:
                    Mochila6();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida");
            
        }
    }
    
}

    public static void Mochila1() {
        // Ejercicio de los 5 objetos iniciales 
        Item[] items = {
            new Item("Bloqueador", 3, 9),
            new Item("Ropa", 5, 10),
            new Item("Sombrilla", 2, 7),
            new Item("Desodorante", 3, 9),
            new Item("Cepillo de dientes",2,10)
        };

        System.out.println("Items de la mochila: ");
        for (Item item : items) {
            System.out.println(item);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Mochila mochila = new Mochila(6, items); // Capacidad de la mochila: 6

        // Resolver usando programación dinámica
        Mochila resultadoDP = Dinamico.resolverMochilaDP(mochila);
        System.out.println("Resultado usando Programación Dinámica: " + resultadoDP);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoDP.getItems()).map(Item::getNombre).toList());

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("/ / / / Genético / / / /");

        // Resolver usando algoritmo genético
        Genetico.TAMANO_POBLACION = 3;
        Mochila resultadoGA = Genetico.resolverMochilaGA(mochila);
        System.out.println("Resultado usando Algoritmo Genético: " + resultadoGA);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoGA.getItems()).map(Item::getNombre).toList());
             //Prueba 5 items
            
    }

    public static void Mochila2(){
        Item[] items2 = {};
        int peso;
        int valor;
        for (int i = 0; i < 10; i++) {
            peso = (int)(Math.random() * 50 + 1);
            valor = (int)(Math.random() * 50 + 1);
            items2 = Arrays.copyOf(items2, items2.length + 1);
            items2[items2.length - 1] = new Item("Item" + (i + 1), peso, valor);
        }

        Mochila mochila2 = new Mochila(50, items2); // Capacidad de la mochila: 50

        //Imprimir todos los valores que se encuentren en items2
        System.out.println("Items generados aleatoriamente de la mochila2: ");
        for (Item item : items2) {
            System.out.println(item);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //Resolver usando programación dinámica
        Mochila resultadoDP2 = Dinamico.resolverMochilaDP(mochila2);
        System.out.println("Resultado usando Programación Dinámica: " + resultadoDP2);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoDP2.getItems()).map(Item::getNombre).toList());


        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("/ / / / Genético / / / /");
        //Resolver usando algoritmo genético
        Genetico.TAMANO_POBLACION = 10;
        Mochila resultadoGA2 = Genetico.resolverMochilaGA(mochila2);
        System.out.println("Resultado usando Algoritmo Genético: " + resultadoGA2);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoGA2.getItems()).map(Item::getNombre).toList());
            //Prueba 10 items. Pero necesito saber si lo hago con los valores generados de esa forma o de otra manera
    }

    public static void Mochila3(){
        Item[] items3 = {};
        int peso3;
        int valor3;
            for (int i = 0; i < 20; i++) {
                peso3 = (int)(Math.random() * 50 + 1);
                valor3 = (int)(Math.random() * 50 + 1);
                items3 = Arrays.copyOf(items3, items3.length + 1);
                items3[items3.length - 1] = new Item("Item" + (i + 1), peso3, valor3);
            }

        Mochila mochila3 = new Mochila(70, items3); // Capacidad de la mochila: 70

        //Imprimir todos los valores que se encuentren en items2
        System.out.println("Items generados aleatoriamente de la mochila3: ");
        for (Item item : items3) {
            System.out.println(item);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //Resolver usando programación dinámica
        Mochila resultadoDP3 = Dinamico.resolverMochilaDP(mochila3);
        System.out.println("Resultado usando Programación Dinámica: " + resultadoDP3);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoDP3.getItems()).map(Item::getNombre).toList());
        
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("/ / / / Genético / / / /");

        //Resolver usando algoritmo genético
        Genetico.TAMANO_POBLACION = 20;
        Mochila resultadoGA3 = Genetico.resolverMochilaGA(mochila3);
        System.out.println("Resultado usando Algoritmo Genético: " + resultadoGA3);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoGA3.getItems()).map(Item::getNombre).toList());
    }


    public static void Mochila4(){
        Item[] items4 = {};
        int peso4;
        int valor4;

        for (int i = 0; i < 30; i++) {
            peso4 = (int)(Math.random() * 50 + 1);
            valor4 = (int)(Math.random() * 50 + 1);
            items4 = Arrays.copyOf(items4, items4.length + 1);
            items4[items4.length - 1] = new Item("Item" + (i + 1), peso4, valor4);
        }

        Mochila mochila4 = new Mochila(100, items4); // Capacidad de la mochila: 100

        //Imprimir todos los valores que se encuentren en items4
        System.out.println("Items generados aleatoriamente de la mochila4: ");
        for (Item item : items4) {
            System.out.println(item);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Resolver usando programación dinámica
        Mochila resultadoDP4 = Dinamico.resolverMochilaDP(mochila4);
        System.out.println("Resultado usando Programación Dinámica: " + resultadoDP4);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoDP4.getItems()).map(Item::getNombre).toList());

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("/ / / / Genético / / / /");
        //Resolver usando algoritmo genético
        Genetico.TAMANO_POBLACION = 30;
        Mochila resultadoGA4 = Genetico.resolverMochilaGA(mochila4);
        System.out.println("Resultado usando Algoritmo Genético: " + resultadoGA4);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoGA4.getItems()).map(Item::getNombre).toList()); 
    }

    public static void Mochila5(){
        Item[] items5 = {};
        int peso5;
        int valor5;

        for (int i = 0; i < 40; i++) {
            peso5 = (int)(Math.random() * 50 + 1);
            valor5 = (int)(Math.random() * 50 + 1);
            items5 = Arrays.copyOf(items5, items5.length + 1);
            items5[items5.length - 1] = new Item("Item" + (i + 1), peso5, valor5);
        }

        Mochila mochila5 = new Mochila(150, items5); // Capacidad de la mochila: 150

        //Imprimir todos los valores que se encuentren en items5
        System.out.println("Items generados aleatoriamente de la mochila5: ");
        for (Item item : items5) {
            System.out.println(item);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Resolver usando programación dinámica
        Mochila resultadoDP5 = Dinamico.resolverMochilaDP(mochila5);
        System.out.println("Resultado usando Programación Dinámica: " + resultadoDP5);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoDP5.getItems()).map(Item::getNombre).toList());

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("/ / / / Genético / / / /");
        //Resolver usando algoritmo genético
        Genetico.TAMANO_POBLACION = 40;
        Mochila resultadoGA5 = Genetico.resolverMochilaGA(mochila5);
        System.out.println("Resultado usando Algoritmo Genético: " + resultadoGA5);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoGA5.getItems()).map(Item::getNombre).toList());
    }

    public static void Mochila6(){
        Item[] items6 = {};
        int peso6;
        int valor6;

        for (int i = 0; i < 50; i++) {
            peso6 = (int)(Math.random() * 50 + 1);
            valor6 = (int)(Math.random() * 50 + 1);
            items6 = Arrays.copyOf(items6, items6.length + 1);
            items6[items6.length - 1] = new Item("Item" + (i + 1), peso6, valor6);
        }

        Mochila mochila6 = new Mochila(200, items6); // Capacidad de la mochila: 200

        //Imprimir todos los valores que se encuentren en items6
        System.out.println("Items generados aleatoriamente de la mochila6: ");
        for (Item item : items6) {
            System.out.println(item);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Resolver usando programación dinámica
        Mochila resultadoDP6 = Dinamico.resolverMochilaDP(mochila6);
        System.out.println("Resultado usando Programación Dinámica: " + resultadoDP6);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoDP6.getItems()).map(Item::getNombre).toList());


        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("/ / / / Genético / / / /");
        //Resolver usando algoritmo genético
        Genetico.TAMANO_POBLACION = 50;
        Mochila resultadoGA6 = Genetico.resolverMochilaGA(mochila6);
        System.out.println("Resultado usando Algoritmo Genético: " + resultadoGA6);
        System.out.println("Items seleccionados: " + 
            Arrays.stream(resultadoGA6.getItems()).map(Item::getNombre).toList());
    }

}


