import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class Genetico {
    // Variables para medicion
    public static long a = 0;
    public static long c = 0;
    public static long lineasEjecutadas = 0;
    public static long memoriaUsada = 0;

    // Tamaño de la población inicial
    static int TAMANO_POBLACION = 20; 
    // Tasa de mutación
    private static final double TASA_MUTACION = 0.05;
    // Número de generaciones a simular
    private static final int NUMERO_GENERACIONES = 20;
    // Penalidad por exceder la capacidad de la mochila
    private static final double PENALIDAD = 1000.0;

    // Método principal que resuelve el problema de la mochila usando un algoritmo genético
    public static Mochila resolverMochilaGA(Mochila mochila) {
        long startTime = System.nanoTime();
        int capacidad = mochila.getCapacidad(); a++;
        Item[] items = mochila.getItems(); a++;
        int n = items.length; a++;

        // Inicializar la población aleatoria
        int[][] poblacion = new int[TAMANO_POBLACION][n]; a++;
        Random random = new Random(); a++;

        // Crear individuos aleatorios en la población
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            c++;
            for (int j = 0; j < n; j++) {
                c++;
                poblacion[i][j] = random.nextBoolean() ? 1 : 0; a++;
            } c++;
        } c++;

        // Variables para almacenar el mejor individuo y su aptitud
        int[] mejorIndividuo = null; a++;
        double mejorAptitud = Double.NEGATIVE_INFINITY; a++;

        // Almacenar las mejores poblaciones y sus puntuaciones
        double[][] mejoresPoblaciones = new double[TAMANO_POBLACION][n + 1]; a++;

        // Iterar sobre el número de generaciones
        for (int generacion = 0; generacion < NUMERO_GENERACIONES; generacion++) {
            c++;
            // Evaluar la aptitud de cada individuo en la población
            double[] aptitud = new double[TAMANO_POBLACION]; a++;
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                c++;
                aptitud[i] = evaluarAptitud(poblacion[i], items, capacidad); a++;
                c++;
                if (aptitud[i] > mejorAptitud) {
                    c++;
                    mejorAptitud = aptitud[i]; a++;
                    mejorIndividuo = Arrays.copyOf(poblacion[i], n); a++;
                }
            } c++;

            // Guardar las mejores poblaciones y sus puntuaciones
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                c++;
                for (int j = 0; j < n; j++) {
                    c++;
                    mejoresPoblaciones[i][j] = poblacion[i][j];
                } 
                c++;
                mejoresPoblaciones[i][n] = aptitud[i]; a++;
            }
            c++;

            // Selección de padres y generación de nueva población mediante cruce
            int[][] nuevaPoblacion = new int[TAMANO_POBLACION][n]; a++;
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                c++;
                int padre1 = seleccionar(aptitud); a++;
                int padre2 = seleccionar(aptitud); a++;

                int[] descendiente = cruzar(poblacion[padre1], poblacion[padre2], items, capacidad); a++;
                nuevaPoblacion[i] = descendiente; a++;

                double puntuacionPadre1 = aptitud[padre1]; a++;
                double puntuacionPadre2 = aptitud[padre2]; a++;
                double puntuacionDescendiente = evaluarAptitud(descendiente, items, capacidad); a++;

                System.out.println("Padre 1: " + Arrays.toString(poblacion[padre1]) + " puntuación: " + puntuacionPadre1);
                System.out.println("Padre 2: " + Arrays.toString(poblacion[padre2]) + " puntuación: " + puntuacionPadre2);
                System.out.println("Descendiente " + i + ": " + Arrays.toString(descendiente) + " puntuación: " + puntuacionDescendiente);
            }

            // Aplicar mutación dirigida y evitar duplicados
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                c++; // Se ejecuta TAMANO_POBLACION veces
                c++; // Por el if
                if (random.nextDouble() < TASA_MUTACION) {
                    mutarDirigida(nuevaPoblacion[i], items, capacidad);
                }
                // Verificar duplicados
                for (int j = 0; j < i; j++) {
                    c++; // por el clclo for
                    c++; // por el if
                    if (Arrays.equals(nuevaPoblacion[i], nuevaPoblacion[j])) {
                        mutar(nuevaPoblacion[i]);
                    }
                } c++; // por la comparacion falsa
            }
            c++; // por la comparacion falsa

            // Implementar elitismo: mantener el mejor individuo en la nueva población
            c++; // por el if
            if (mejorIndividuo != null) {
                nuevaPoblacion[0] = mejorIndividuo; a++;
            }

            poblacion = nuevaPoblacion; a++;
        }

        // Ordenar las poblaciones por puntuación
        Arrays.sort(mejoresPoblaciones, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o2[n], o1[n]);
            }
        });

        // Imprimir las mejores poblaciones y sus puntuaciones
        c++; // por el if
        if (TAMANO_POBLACION == 3) {
            System.out.println("Las 3 mejores poblaciones:");
            System.out.println("Población 1: " + Arrays.toString(Arrays.copyOfRange(mejoresPoblaciones[0], 0, n)) +
                    " puntuación: " + mejoresPoblaciones[0][n]);
            System.out.println("Población 2: " + Arrays.toString(Arrays.copyOfRange(mejoresPoblaciones[1], 0, n)) +
                    " puntuación: " + mejoresPoblaciones[1][n]);
            System.out.println("Población 3: " + Arrays.toString(Arrays.copyOfRange(mejoresPoblaciones[2], 0, n)) +
                    " puntuación: " + mejoresPoblaciones[2][n]);
        } else { c++; // por el else
            System.out.println("Las 5 mejores poblaciones:");
            for (int i = 0; i < 5; i++) {
                System.out.println("Población " + (i + 1) + ": " + Arrays.toString(Arrays.copyOfRange(mejoresPoblaciones[i], 0, n)) +
                        " puntuación: " + mejoresPoblaciones[i][n]);
            }
        }

        // Construir la lista de items seleccionados
        int pesoTotalFinal = 0; a++;
        int valorTotalFinal = 0; a++;
        int count = 0; a++;
        
        for (int b : mejorIndividuo) {
            if (b == 1) count++;
            c++; // por el for
        }
        c++; // por la comparacion falsa

        Item[] itemsSeleccionados = new Item[count]; a++;
        int index = 0; a++;
        for (int i = 0; i < mejorIndividuo.length; i++) {
            c++; // por el for
            c++; // por el if
            if (mejorIndividuo[i] == 1) {
                itemsSeleccionados[index++] = items[i];
                pesoTotalFinal += items[i].getPeso();
                valorTotalFinal += items[i].getValor();
            }
            c++; // por la comparacion falsa
        }

        Mochila resultado = new Mochila(mochila.getCapacidad(), itemsSeleccionados); a++;
        resultado.setPesoTotal(pesoTotalFinal); a++;
        resultado.setValorTotal(valorTotalFinal); a++;
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        long durationInMs = TimeUnit.NANOSECONDS.toMillis(duration);

        lineasEjecutadas = a + c;
        System.out.println("Cantidad de asignaciones geneticas: " + a);
        System.out.println("Cantidad de comparaciones geneticas: " + c);
        System.out.println("Cantidad de lineas ejecutadas geneticas: " + lineasEjecutadas);
        System.out.println("Tiempos de ejecucion geneticas: " + durationInMs + " ms");

        
        a = 0;
        c = 0;
        

        return resultado;
    }

    // Método para evaluar la aptitud de un individuo
    private static double evaluarAptitud(int[] individuo, Item[] items, int capacidad) {
        int pesoTotal = 0; a++;
        int valorTotal = 0; a++;
        for (int i = 0; i < individuo.length; i++) {
            c++; // por el for
            c++; // por el if
            if (individuo[i] == 1) {
                pesoTotal += items[i].getPeso(); a++;
                valorTotal += items[i].getValor(); a++;
            }
        }
        c++; // por la comparacion falsa
        if (pesoTotal <= capacidad) {
            return valorTotal;
        } else {
            return valorTotal - PENALIDAD * (pesoTotal - capacidad);
        }
    }

    // Método para seleccionar un individuo basado en su aptitud
    private static int seleccionar(double[] aptitud) {
        Random random = new Random(); a++;
        double totalAptitud = Arrays.stream(aptitud).sum(); a++;

        c++; // por el if
        if (totalAptitud <= 0) {
            return random.nextInt(aptitud.length);
        }

        double punto = random.nextDouble() * totalAptitud; a++;
        double suma = 0; a++;
        for (int i = 0; i < aptitud.length; i++) {
            c++; // por el for
            suma += aptitud[i]; a++;
            c++; // por el if
            if (suma > punto) {
                return i;
            }
        }
        c++; // por la comparacion falsa
        return aptitud.length - 1;
    }

    // Método para cruzar dos padres y generar un descendiente
    private static int[] cruzar(int[] padre1, int[] padre2, Item[] items, int capacidad) {
        Random random = new Random(); a++;
        int puntoCruce1 = random.nextInt(padre1.length); a++;
        int puntoCruce2 = random.nextInt(padre1.length - puntoCruce1) + puntoCruce1; a++;
        int[] descendiente = new int[padre1.length]; a++;
        StringBuilder descendienteOrigen = new StringBuilder(); a++;

        for (int i = 0; i < puntoCruce1; i++) {
            c++; // por el for
            descendiente[i] = padre1[i];
            descendienteOrigen.append("P1 ");
        }
        c++; // por la comparacion falsa
        for (int i = puntoCruce1; i < puntoCruce2; i++) {
            c++; // por el for
            descendiente[i] = padre2[i];
            descendienteOrigen.append("P2 ");
        }
        c++; // por la comparacion falsa
        for (int i = puntoCruce2; i < padre2.length; i++) {
            c++; // por el for
            descendiente[i] = padre1[i];
            descendienteOrigen.append("P1 ");
        }
        c++; // por la comparacion falsa

        corregir(descendiente, items, capacidad);
        System.out.println("Origen descendiente: " + descendienteOrigen);

        return descendiente;
    }

    // Método para mutar un individuo
    private static void mutar(int[] individuo) {
        Random random = new Random(); a++;
        for (int i = 0; i < individuo.length; i++) {
            c++; // por el for
            c++; // por el if
            if (random.nextDouble() < TASA_MUTACION) {
                individuo[i] = 1 - individuo[i];
            }
        }
        c++; // por la comparacion falsa
    }

    // Método para aplicar una mutación dirigida en un individuo
    private static void mutarDirigida(int[] individuo, Item[] items, int capacidad) {
        Random random = new Random(); a++;
        int intento = 0; a++;
        while (intento < 10) {
            c++; // por el while
            int index = random.nextInt(individuo.length); a++;
            individuo[index] = 1 - individuo[index]; a++;

            int pesoTotal = 0; a++;
            for (int i = 0; i < individuo.length; i++) {
                c++; // por el for
                c++; // por el if
                if (individuo[i] == 1) {
                    pesoTotal += items[i].getPeso();
                }

            } c++; // por la comparacion falsa

            c++; // por el if
            if (pesoTotal <= capacidad) {
                return;
            }
            else c++; /*por el else */{
                individuo[index] = 1 - individuo[index];
            }

            intento++;
        }
        c++; // por la comparacion falsa
    }

    // Método para corregir un individuo que excede la capacidad
    private static void corregir(int[] individuo, Item[] items, int capacidad) {
        Random random = new Random(); a++;
        int pesoTotal = 0; a++;
        for (int i = 0; i < individuo.length; i++) {
            c++; // por el for
            c++; // por el if
            if (individuo[i] == 1) {
                pesoTotal += items[i].getPeso();
            }
        } c++; // por la comparacion falsa

        while (pesoTotal > capacidad) {
            c++; // por el while
            int index = random.nextInt(individuo.length); a++;
            c++; // por el if
            if (individuo[index] == 1) {
                individuo[index] = 0;
                pesoTotal -= items[index].getPeso();
            }
        }
    }
}
