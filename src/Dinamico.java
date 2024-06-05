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
        long startTime = System.nanoTime(); memoriaUsada += 64;
        // Obtener la capacidad de la mochila y los ítems disponibles
        int capacidad = mochila.getCapacidad(); a++; memoriaUsada += 32;
        Item[] items = mochila.getItems(); a++; memoriaUsada += 32;
        int n = items.length; a++; memoriaUsada += 32;

        // Crear una matriz dp para almacenar los valores máximos
        int[][] dp = new int[n + 1][capacidad + 1]; a++; memoriaUsada += 32;

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
                                        dp[i - 1][w - items[i - 1].getPeso()] + items[i - 1].getValor()); memoriaUsada += 32;
                    a++;
                } else {
                    c++;
                    // No incluir el ítem actual
                    dp[i][w] = dp[i - 1][w]; memoriaUsada += 32;
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
        int w = capacidad; a++; memoriaUsada += 32;
        int valorTotal = dp[n][w]; a++; memoriaUsada += 32;
        int pesoTotal = 0; a++; memoriaUsada += 32;
        boolean[] seleccionados = new boolean[n]; a++; memoriaUsada += 2;
        
        for (int i = n; i > 0 && valorTotal > 0; i--) {
            c+= 2;
            // Si el valor total actual no es el mismo que el valor en la fila anterior, significa que se incluyó el ítem actual
            c++;
            if (valorTotal != dp[i - 1][w]) {
                seleccionados[i - 1] = true; a++; memoriaUsada += 2;
                pesoTotal += items[i - 1].getPeso(); a++; memoriaUsada += 32;
                valorTotal -= items[i - 1].getValor(); a++; memoriaUsada += 32;
                w -= items[i - 1].getPeso(); a++; memoriaUsada += 32;
            }
        }
        c++;

        // Crear un array con los ítems seleccionados
        Item[] itemsSeleccionados = java.util.stream.IntStream.range(0, n)
                                                             .filter(i -> seleccionados[i])
                                                             .mapToObj(i -> items[i])
                                                             .toArray(Item[]::new); memoriaUsada += 32;
        a++;

        // Crear una nueva mochila con los ítems seleccionados
        Mochila resultado = new Mochila(mochila.getCapacidad(), itemsSeleccionados); a++; memoriaUsada += 32;
        resultado.setPesoTotal(pesoTotal); a++; memoriaUsada += 32;
        resultado.setValorTotal(dp[n][capacidad]); a++; memoriaUsada += 32;
        long endTime = System.nanoTime();

        long tiempoEjecucion = endTime - startTime;
        long durationInMs = TimeUnit.NANOSECONDS.toMillis(tiempoEjecucion); 
        // Resultados finales
        System.out.println("Asignaciones dinamicas: " + a);
        System.out.println("Comparaciones dinamicas: " + c);
        lineasEjecutadas = a + c;
        System.out.println("Lineas ejecutadas: " + lineasEjecutadas);  
        System.out.println("Tiempo de ejecucion: " + durationInMs + " ms");
        System.out.println("Memoria usada: " + memoriaUsada + " bits");

        // Retornar la mochila con la solución óptima
        return resultado;
    }

    // Método para imprimir los resultados en una etapa específica
    private static void imprimirResultados(int[][] dp, int etapa, Item[] items, int capacidad) {
        System.out.println("Resultados en la etapa " + etapa + ":"); memoriaUsada += 32;
        int valorTotal = dp[etapa][capacidad]; a++; memoriaUsada += 32;
        int pesoTotal = 0; a++; memoriaUsada += 32;
        boolean[] seleccionados = new boolean[etapa]; a++; memoriaUsada += 2;
        
        int w = capacidad; a++; memoriaUsada += 32;
        for (int i = etapa; i > 0 && valorTotal > 0; i--) {
            c+= 2;
            // Si el valor total actual no es el mismo que el valor en la fila anterior, significa que se incluyó el ítem actual
            c++;
            if (valorTotal != dp[i - 1][w]) {
                seleccionados[i - 1] = true; a++; memoriaUsada += 2;
                pesoTotal += items[i - 1].getPeso(); a++; memoriaUsada += 32;
                valorTotal -= items[i - 1].getValor(); a++; memoriaUsada += 32; 
                w -= items[i - 1].getPeso(); a++; memoriaUsada += 32;
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