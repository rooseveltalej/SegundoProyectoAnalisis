import java.util.Arrays;
import java.util.Random;

public class Genetico {
    private static final int TAMANO_POBLACION = 10; // La cantidad de objetos que se van a evaluar
    private static final double TASA_MUTACION = 0.05;
    private static final int NUMERO_GENERACIONES = 20;

    public static Mochila resolverMochilaGA(Mochila mochila) {
        int capacidad = mochila.getCapacidad();
        Item[] items = mochila.getItems();
        int n = items.length;

        // Verificar si todos los items tienen un peso mayor que la capacidad de la mochila
        boolean todosItemsMuyPesados = true;
        for (Item item : items) {
            if (item.getPeso() <= capacidad) {
                todosItemsMuyPesados = false;
                break;
            }
        }
        if (todosItemsMuyPesados) {
            return new Mochila(capacidad, new Item[0]);
        }

        // Inicializar población
        boolean[][] poblacion = new boolean[TAMANO_POBLACION][n];
        Random random = new Random();

        for (int i = 0; i < TAMANO_POBLACION; i++) {
            for (int j = 0; j < n; j++) {
                poblacion[i][j] = random.nextBoolean();
            }
        }

        int[][] padres = new int[TAMANO_POBLACION][2]; // Arreglo para almacenar los padres de cada hijo

        for (int generacion = 0; generacion < NUMERO_GENERACIONES; generacion++) {
            // Evaluar aptitud
            int[] aptitud = new int[TAMANO_POBLACION];
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                int pesoTotal = 0;
                int valorTotal = 0;
                for (int j = 0; j < n; j++) {
                    if (poblacion[i][j]) {
                        pesoTotal += items[j].getPeso();
                        valorTotal += items[j].getValor();
                    }
                }
                if (pesoTotal <= capacidad) {
                    aptitud[i] = valorTotal;
                } else {
                    aptitud[i] = 0;
                }
            }

            // Selección
            boolean[][] nuevaPoblacion = new boolean[TAMANO_POBLACION][n];
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                int padre1 = seleccionar(aptitud);
                int padre2 = seleccionar(aptitud);
                padres[i][0] = padre1;
                padres[i][1] = padre2;
                nuevaPoblacion[i] = cruzar(poblacion[padre1], poblacion[padre2]);
            }

            // Mutación y verificación de duplicados
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                if (random.nextDouble() < TASA_MUTACION) {
                    mutar(nuevaPoblacion[i]);
                }
                // Verificar duplicados
                for (int j = 0; j < i; j++) {
                    if (Arrays.equals(nuevaPoblacion[i], nuevaPoblacion[j])) {
                        mutar(nuevaPoblacion[i]);
                    }
                }
                // Verificar con los padres
                if (Arrays.equals(nuevaPoblacion[i], poblacion[padres[i][0]]) ||
                    Arrays.equals(nuevaPoblacion[i], poblacion[padres[i][1]])) {
                    mutar(nuevaPoblacion[i]);
                }
            }

            poblacion = nuevaPoblacion;
        }

        // Imprimir la nueva población con sus pesos y valores
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            int pesoTotal = 0;
            int valorTotal = 0;
            for (int j = 0; j < n; j++) {
                if (poblacion[i][j]) {
                    System.out.print("1 ");
                    pesoTotal += items[j].getPeso();
                    valorTotal += items[j].getValor();
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println("Peso: " + pesoTotal + " Valor: " + valorTotal + " Padres: [" + padres[i][0] + ", " + padres[i][1] + "]");
        }

        // Encontrar la mejor solución
        int mejorAptitud = 0;
        boolean[] mejorIndividuo = null;
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            int pesoTotal = 0;
            int valorTotal = 0;
            for (int j = 0; j < n; j++) {
                if (poblacion[i][j]) {
                    pesoTotal += items[j].getPeso();
                    valorTotal += items[j].getValor();
                }
            }
            if (pesoTotal <= capacidad && valorTotal > mejorAptitud) {
                mejorAptitud = valorTotal;
                mejorIndividuo = poblacion[i];
            }
        }

        // Construir la lista de items seleccionados
        int pesoTotalFinal = 0;
        int valorTotalFinal = 0;
        int count = 0;
        try{
            for (boolean b : mejorIndividuo) {
                if (b) count++;
            }
        }catch(NullPointerException e){
            System.out.println("No se encontró un mejor individuo");
        }
        Item[] itemsSeleccionados = new Item[count];
        int index = 0;
        if (mejorIndividuo != null) {
            for (int i = 0; i < mejorIndividuo.length; i++) {
                if (mejorIndividuo[i]) {
                    itemsSeleccionados[index++] = items[i];
                    pesoTotalFinal += items[i].getPeso();
                    valorTotalFinal += items[i].getValor();
                }
            }
        }
        else{
            System.out.println("No se encontró un mejor individuo");
        }
        

        Mochila resultado = new Mochila(mochila.getCapacidad(), itemsSeleccionados);
        resultado.setPesoTotal(pesoTotalFinal);
        resultado.setValorTotal(valorTotalFinal);

        return resultado;
    }

    private static int seleccionar(int[] aptitud) {
        Random random = new Random();
        int totalAptitud = Arrays.stream(aptitud).sum();

        // Verificar si la suma total de aptitud es cero
        if (totalAptitud <= 0) {
            // Si es así, seleccionar un individuo aleatoriamente
            return random.nextInt(aptitud.length);
        }

        int punto = random.nextInt(totalAptitud);
        int suma = 0;
        for (int i = 0; i < aptitud.length; i++) {
            suma += aptitud[i];
            if (suma > punto) {
                return i;
            }
        }
        return aptitud.length - 1; // Fallback por si no se cumple la condición en el bucle
    }

    private static boolean[] cruzar(boolean[] padre1, boolean[] padre2) {
        Random random = new Random();
        int puntoCruce = random.nextInt(padre1.length);
        boolean[] descendiente = new boolean[padre1.length];
        for (int i = 0; i < puntoCruce; i++) {
            descendiente[i] = padre1[i];
        }
        for (int i = puntoCruce; i < padre2.length; i++) {
            descendiente[i] = padre2[i];
        }
        return descendiente;
    }

    private static void mutar(boolean[] individuo) {
        Random random = new Random();
        for (int i = 0; i < individuo.length; i++) {
            if (random.nextDouble() < TASA_MUTACION) {
                individuo[i] = !individuo[i];
            }
        }
    }
}
