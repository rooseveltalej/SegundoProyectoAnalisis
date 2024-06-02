import java.util.Arrays;
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
        boolean[][] poblacion = new boolean[TAMANO_POBLACION][n];
        Random random = new Random();

        for (int i = 0; i < TAMANO_POBLACION; i++) {
            for (int j = 0; j < n; j++) {
                poblacion[i][j] = random.nextBoolean();
            }
        }

        boolean[] mejorIndividuo = null;
        double mejorAptitud = Double.NEGATIVE_INFINITY;

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

            // Selección
            boolean[][] nuevaPoblacion = new boolean[TAMANO_POBLACION][n];
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                int padre1 = seleccionar(aptitud);
                int padre2 = seleccionar(aptitud);
                nuevaPoblacion[i] = cruzar(poblacion[padre1], poblacion[padre2], items, capacidad);
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

        // Construir la lista de items seleccionados
        int pesoTotalFinal = 0;
        int valorTotalFinal = 0;
        int count = 0;
        for (boolean b : mejorIndividuo) {
            if (b) count++;
        }

        Item[] itemsSeleccionados = new Item[count];
        int index = 0;
        for (int i = 0; i < mejorIndividuo.length; i++) {
            if (mejorIndividuo[i]) {
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

    private static double evaluarAptitud(boolean[] individuo, Item[] items, int capacidad) {
        int pesoTotal = 0;
        int valorTotal = 0;
        for (int i = 0; i < individuo.length; i++) {
            if (individuo[i]) {
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

    private static boolean[] cruzar(boolean[] padre1, boolean[] padre2, Item[] items, int capacidad) {
        Random random = new Random();
        int puntoCruce1 = random.nextInt(padre1.length);
        int puntoCruce2 = random.nextInt(padre1.length - puntoCruce1) + puntoCruce1;
        boolean[] descendiente = new boolean[padre1.length];
        for (int i = 0; i < puntoCruce1; i++) {
            descendiente[i] = padre1[i];
        }
        for (int i = puntoCruce1; i < puntoCruce2; i++) {
            descendiente[i] = padre2[i];
        }
        for (int i = puntoCruce2; i < padre2.length; i++) {
            descendiente[i] = padre1[i];
        }
        // Corregir si el descendiente excede la capacidad
        corregir(descendiente, items, capacidad);
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

    private static void mutarDirigida(boolean[] individuo, Item[] items, int capacidad) {
        Random random = new Random();
        int n = individuo.length;
        int intento = 0;
        while (intento < 10) {
            int index = random.nextInt(n);
            individuo[index] = !individuo[index];

            int pesoTotal = 0;
            for (int i = 0; i < n; i++) {
                if (individuo[i]) {
                    pesoTotal += items[i].getPeso();
                }
            }

            if (pesoTotal <= capacidad) {
                return;
            } else {
                individuo[index] = !individuo[index];
            }
            intento++;
        }
    }

    private static void corregir(boolean[] individuo, Item[] items, int capacidad) {
        int pesoTotal = 0;
        for (int i = 0; i < individuo.length; i++) {
            if (individuo[i]) {
                pesoTotal += items[i].getPeso();
            }
        }

        Random random = new Random();
        while (pesoTotal > capacidad) {
            int index = random.nextInt(individuo.length);
            if (individuo[index]) {
                individuo[index] = false;
                pesoTotal -= items[index].getPeso();
            }
        }
    }
}
