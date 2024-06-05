// Fecha de creación: 10/05/2024

import java.util.concurrent.TimeUnit;

public class Dinamico {
    // Variables de medicion
    public static long a = 0;
    public static long c = 0;
    public static long lineasEjecutadas = 0;
    public static long memoriaUsada = 0;
    // Método para resolver el problema de la mochila utilizando programación dinámica
    public static Mochila resolverMochilaDP(Mochila mochila) {
        long startTime = System.nanoTime();
        // Obtener la capacidad de la mochila y los ítems disponibles
        int capacidad = mochila.getCapacidad(); a++;
        Item[] items = mochila.getItems(); a++;
        int n = items.length; a++;

        // Crear una matriz dp para almacenar los valores máximos
        int[][] dp = new int[n + 1][capacidad + 1]; a++;

        // Llenar la tabla dp usando el algoritmo de programación dinámica
        for (int i = 1; i <= n; i++) {
            c++;
            for (int w = 0; w <= capacidad; w++) {
                c++;
                // Si el peso del ítem actual es menor o igual al peso w
                c++;
                if (items[i - 1].getPeso() <= w) {
                    // Decidir si incluir el ítem actual o no
                    dp[i][w] = Math.max(dp[i - 1][w],
                                        dp[i - 1][w - items[i - 1].getPeso()] + items[i - 1].getValor());
                    a++;
                } else {
                    c++;
                    // No incluir el ítem actual
                    dp[i][w] = dp[i - 1][w];
                    a++;
                }
            }
            // Imprimir los resultados cada 5 etapas o en la última etapa
            c+=2;
            if (i % 5 == 0 || i == n) {
                imprimirResultados(dp, i, items, capacidad);
            }
        }

        // Encontrar los ítems seleccionados
        int w = capacidad; a++;
        int valorTotal = dp[n][w]; a++;
        int pesoTotal = 0; a++;
        boolean[] seleccionados = new boolean[n]; a++;
        
        for (int i = n; i > 0 && valorTotal > 0; i--) {
            c+= 2;
            // Si el valor total actual no es el mismo que el valor en la fila anterior, significa que se incluyó el ítem actual
            c++;
            if (valorTotal != dp[i - 1][w]) {
                seleccionados[i - 1] = true; a++;
                pesoTotal += items[i - 1].getPeso(); a++;
                valorTotal -= items[i - 1].getValor(); a++;
                w -= items[i - 1].getPeso(); a++;
            }
        }
        c++;

        // Crear un array con los ítems seleccionados
        Item[] itemsSeleccionados = java.util.stream.IntStream.range(0, n)
                                                             .filter(i -> seleccionados[i])
                                                             .mapToObj(i -> items[i])
                                                             .toArray(Item[]::new);
        a++;

        // Crear una nueva mochila con los ítems seleccionados
        Mochila resultado = new Mochila(mochila.getCapacidad(), itemsSeleccionados); a++;
        resultado.setPesoTotal(pesoTotal); a++;
        resultado.setValorTotal(dp[n][capacidad]); a++;
        long endTime = System.nanoTime();

        long tiempoEjecucion = endTime - startTime;
        long durationInMs = TimeUnit.NANOSECONDS.toMillis(tiempoEjecucion);
        // Resultados finales
        System.out.println("Asignaciones dinamicas: " + a);
        System.out.println("Comparaciones dinamicas: " + c);
        lineasEjecutadas = a + c;
        System.out.println("Lineas ejecutadas: " + lineasEjecutadas);  
        System.out.println("Tiempo de ejecucion: " + durationInMs + " ms");

        // Retornar la mochila con la solución óptima
        return resultado;
    }

    // Método para imprimir los resultados en una etapa específica
    private static void imprimirResultados(int[][] dp, int etapa, Item[] items, int capacidad) {
        System.out.println("Resultados en la etapa " + etapa + ":");
        int valorTotal = dp[etapa][capacidad]; a++;
        int pesoTotal = 0; a++;
        boolean[] seleccionados = new boolean[etapa]; a++;
        
        int w = capacidad; a++;
        for (int i = etapa; i > 0 && valorTotal > 0; i--) {
            c+= 2;
            // Si el valor total actual no es el mismo que el valor en la fila anterior, significa que se incluyó el ítem actual
            c++;
            if (valorTotal != dp[i - 1][w]) {
                seleccionados[i - 1] = true; a++;
                pesoTotal += items[i - 1].getPeso(); a++;
                valorTotal -= items[i - 1].getValor(); a++;
                w -= items[i - 1].getPeso(); a++;
            }
        }

        // Imprimir el valor total y el peso total en la etapa actual
        System.out.println("Valor total: " + dp[etapa][capacidad]);
        System.out.println("Peso total: " + pesoTotal);

        // Imprimir los nombres de los ítems seleccionados en la etapa actual
        System.out.print("Items seleccionados: ");
        for (int i = 0; i < seleccionados.length; i++) {
            c++;
            c++; // Por el if
            if (seleccionados[i]) {
                System.out.print(items[i].getNombre() + " ");
            }
        }
        System.out.println();
        System.out.println();
    }
}