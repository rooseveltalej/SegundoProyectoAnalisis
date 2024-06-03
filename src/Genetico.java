import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Genetico {
    static int TAMANO_POBLACION = 20;
    private static final double TASA_MUTACION = 0.05;
    private static final int NUMERO_GENERACIONES = 20;
    private static final double PENALIDAD = 1000.0;

    public static Mochila resolverMochilaGA(Mochila mochila) {
        int capacidad = mochila.getCapacidad();
        Item[] items = mochila.getItems();
        int n = items.length;

        // Inicializar población
        int[][] poblacion = new int[TAMANO_POBLACION][n];
        Random random = new Random();

        for (int i = 0; i < TAMANO_POBLACION; i++) {
            for (int j = 0; j < n; j++) {
                poblacion[i][j] = random.nextBoolean() ? 1 : 0;
            }
        }

        int[] mejorIndividuo = null;
        double mejorAptitud = Double.NEGATIVE_INFINITY;

        // Almacenar las mejores poblaciones y sus puntuaciones
        double[][] mejoresPoblaciones = new double[TAMANO_POBLACION][n + 1];

        for (int generacion = 0; generacion < NUMERO_GENERACIONES; generacion++) {
            // Evaluar aptitud
            double[] aptitud = new double[TAMANO_POBLACION];
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                aptitud[i] = evaluarAptitud(poblacion[i], items, capacidad);
                if (aptitud[i] > mejorAptitud) {
                    mejorAptitud = aptitud[i];
                    mejorIndividuo = Arrays.copyOf(poblacion[i], n);
                }
            }

            // Guardar las mejores poblaciones y sus puntuaciones
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                for (int j = 0; j < n; j++) {
                    mejoresPoblaciones[i][j] = poblacion[i][j];
                }
                mejoresPoblaciones[i][n] = aptitud[i];
            }

            // Selección
            int[][] nuevaPoblacion = new int[TAMANO_POBLACION][n];
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                int padre1 = seleccionar(aptitud);
                int padre2 = seleccionar(aptitud);

                int[] descendiente = cruzar(poblacion[padre1], poblacion[padre2], items, capacidad);
                nuevaPoblacion[i] = descendiente;

                double puntuacionPadre1 = aptitud[padre1];
                double puntuacionPadre2 = aptitud[padre2];
                double puntuacionDescendiente = evaluarAptitud(descendiente, items, capacidad);

                System.out.println("Padre 1: " + Arrays.toString(poblacion[padre1]) + " puntuación: " + puntuacionPadre1);
                System.out.println("Padre 2: " + Arrays.toString(poblacion[padre2]) + " puntuación: " + puntuacionPadre2);
                System.out.println("Descendiente " + i + ": " + Arrays.toString(descendiente) + " puntuación: " + puntuacionDescendiente);
            }

            // Mutación dirigida y evitar duplicados
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                if (random.nextDouble() < TASA_MUTACION) {
                    mutarDirigida(nuevaPoblacion[i], items, capacidad);
                }
                // Verificar duplicados
                for (int j = 0; j < i; j++) {
                    if (Arrays.equals(nuevaPoblacion[i], nuevaPoblacion[j])) {
                        mutar(nuevaPoblacion[i]);
                    }
                }
            }

            // Implementar elitismo: mantener el mejor individuo en la nueva población
            if (mejorIndividuo != null) {
                nuevaPoblacion[0] = mejorIndividuo;
            }

            poblacion = nuevaPoblacion;
        }

        // Ordenar las poblaciones por puntuación
        Arrays.sort(mejoresPoblaciones, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o2[n], o1[n]);
            }
        });

        // Imprimir las 5 mejores poblaciones y sus puntuaciones
        
        if (TAMANO_POBLACION == 3){
            System.out.println("Las 3 mejores poblaciones:");
            System.out.println("Población 1: " + Arrays.toString(Arrays.copyOfRange(mejoresPoblaciones[0], 0, n)) +
                    " puntuación: " + mejoresPoblaciones[0][n]);
            System.out.println("Población 2: " + Arrays.toString(Arrays.copyOfRange(mejoresPoblaciones[1], 0, n)) +
                    " puntuación: " + mejoresPoblaciones[1][n]);
            System.out.println("Población 3: " + Arrays.toString(Arrays.copyOfRange(mejoresPoblaciones[2], 0, n)) +
                    " puntuación: " + mejoresPoblaciones[2][n]);
        }
        else{
            System.out.println("Las 5 mejores poblaciones:");
            for (int i = 0; i < 5; i++) {
                System.out.println("Población " + (i + 1) + ": " + Arrays.toString(Arrays.copyOfRange(mejoresPoblaciones[i], 0, n)) +
                        " puntuación: " + mejoresPoblaciones[i][n]);
            }
        }

        // Construir la lista de items seleccionados
        int pesoTotalFinal = 0;
        int valorTotalFinal = 0;
        int count = 0;
        for (int b : mejorIndividuo) {
            if (b == 1) count++;
        }

        Item[] itemsSeleccionados = new Item[count];
        int index = 0;
        for (int i = 0; i < mejorIndividuo.length; i++) {
            if (mejorIndividuo[i] == 1) {
                itemsSeleccionados[index++] = items[i];
                pesoTotalFinal += items[i].getPeso();
                valorTotalFinal += items[i].getValor();
            }
        }

        Mochila resultado = new Mochila(mochila.getCapacidad(), itemsSeleccionados);
        resultado.setPesoTotal(pesoTotalFinal);
        resultado.setValorTotal(valorTotalFinal);

        return resultado;
    }

    private static double evaluarAptitud(int[] individuo, Item[] items, int capacidad) {
        int pesoTotal = 0;
        int valorTotal = 0;
        for (int i = 0; i < individuo.length; i++) {
            if (individuo[i] == 1) {
                pesoTotal += items[i].getPeso();
                valorTotal += items[i].getValor();
            }
        }
        if (pesoTotal <= capacidad) {
            return valorTotal;
        } else {
            return valorTotal - PENALIDAD * (pesoTotal - capacidad);
        }
    }

    private static int seleccionar(double[] aptitud) {
        Random random = new Random();
        double totalAptitud = Arrays.stream(aptitud).sum();

        if (totalAptitud <= 0) {
            return random.nextInt(aptitud.length);
        }

        double punto = random.nextDouble() * totalAptitud;
        double suma = 0;
        for (int i = 0; i < aptitud.length; i++) {
            suma += aptitud[i];
            if (suma > punto) {
                return i;
            }
        }
        return aptitud.length - 1;
    }

    private static int[] cruzar(int[] padre1, int[] padre2, Item[] items, int capacidad) {
        Random random = new Random();
        int puntoCruce1 = random.nextInt(padre1.length);
        int puntoCruce2 = random.nextInt(padre1.length - puntoCruce1) + puntoCruce1;
        int[] descendiente = new int[padre1.length];
        StringBuilder descendienteOrigen = new StringBuilder();

        for (int i = 0; i < puntoCruce1; i++) {
            descendiente[i] = padre1[i];
            descendienteOrigen.append("P1 ");
        }
        for (int i = puntoCruce1; i < puntoCruce2; i++) {
            descendiente[i] = padre2[i];
            descendienteOrigen.append("P2 ");
        }
        for (int i = puntoCruce2; i < padre2.length; i++) {
            descendiente[i] = padre1[i];
            descendienteOrigen.append("P1 ");
        }
        // Corregir si el descendiente excede la capacidad
        corregir(descendiente, items, capacidad);

        System.out.println("Origen descendiente: " + descendienteOrigen.toString().trim());

        return descendiente;
    }

    private static void mutar(int[] individuo) {
        Random random = new Random();
        for (int i = 0; i < individuo.length; i++) {
            if (random.nextDouble() < TASA_MUTACION) {
                individuo[i] = 1 - individuo[i];
            }
        }
    }

    private static void mutarDirigida(int[] individuo, Item[] items, int capacidad) {
        Random random = new Random();
        int n = individuo.length;
        int intento = 0;
        while (intento < 10) {
            int index = random.nextInt(n);
            individuo[index] = 1 - individuo[index];

            int pesoTotal = 0;
            for (int i = 0; i < n; i++) {
                if (individuo[i] == 1) {
                    pesoTotal += items[i].getPeso();
                }
            }

            if (pesoTotal <= capacidad) {
                return;
            } else {
                individuo[index] = 1 - individuo[index];
            }
            intento++;
        }
    }

    private static void corregir(int[] individuo, Item[] items, int capacidad) {
        int pesoTotal = 0;
        for (int i = 0; i < individuo.length; i++) {
            if (individuo[i] == 1) {
                pesoTotal += items[i].getPeso();
            }
        }

        Random random = new Random();
        while (pesoTotal > capacidad) {
            int index = random.nextInt(individuo.length);
            if (individuo[index] == 1) {
                individuo[index] = 0;
                pesoTotal -= items[index].getPeso();
            }
        }
    }
}
