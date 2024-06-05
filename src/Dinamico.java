// Fecha de creación: 10/05/2024
public class Dinamico {
    // 

    // Método para resolver el problema de la mochila utilizando programación dinámica
    public static Mochila resolverMochilaDP(Mochila mochila) {
        // Obtener la capacidad de la mochila y los ítems disponibles
        int capacidad = mochila.getCapacidad();
        Item[] items = mochila.getItems();
        int n = items.length;

        // Crear una matriz dp para almacenar los valores máximos
        int[][] dp = new int[n + 1][capacidad + 1];

        // Llenar la tabla dp usando el algoritmo de programación dinámica
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacidad; w++) {
                // Si el peso del ítem actual es menor o igual al peso w
                if (items[i - 1].getPeso() <= w) {
                    // Decidir si incluir el ítem actual o no
                    dp[i][w] = Math.max(dp[i - 1][w],
                                        dp[i - 1][w - items[i - 1].getPeso()] + items[i - 1].getValor());
                } else {
                    // No incluir el ítem actual
                    dp[i][w] = dp[i - 1][w];
                }
            }
            // Imprimir los resultados cada 5 etapas o en la última etapa
            if (i % 5 == 0 || i == n) {
                imprimirResultados(dp, i, items, capacidad);
            }
        }

        // Encontrar los ítems seleccionados
        int w = capacidad;
        int valorTotal = dp[n][w];
        int pesoTotal = 0;
        boolean[] seleccionados = new boolean[n];
        
        for (int i = n; i > 0 && valorTotal > 0; i--) {
            // Si el valor total actual no es el mismo que el valor en la fila anterior, significa que se incluyó el ítem actual
            if (valorTotal != dp[i - 1][w]) {
                seleccionados[i - 1] = true;
                pesoTotal += items[i - 1].getPeso();
                valorTotal -= items[i - 1].getValor();
                w -= items[i - 1].getPeso();
            }
        }

        // Crear un array con los ítems seleccionados
        Item[] itemsSeleccionados = java.util.stream.IntStream.range(0, n)
                                                             .filter(i -> seleccionados[i])
                                                             .mapToObj(i -> items[i])
                                                             .toArray(Item[]::new);

        // Crear una nueva mochila con los ítems seleccionados
        Mochila resultado = new Mochila(mochila.getCapacidad(), itemsSeleccionados);
        resultado.setPesoTotal(pesoTotal);
        resultado.setValorTotal(dp[n][capacidad]);

        // Retornar la mochila con la solución óptima
        return resultado;
    }

    // Método para imprimir los resultados en una etapa específica
    private static void imprimirResultados(int[][] dp, int etapa, Item[] items, int capacidad) {
        System.out.println("Resultados en la etapa " + etapa + ":");
        int valorTotal = dp[etapa][capacidad];
        int pesoTotal = 0;
        boolean[] seleccionados = new boolean[etapa];
        
        int w = capacidad;
        for (int i = etapa; i > 0 && valorTotal > 0; i--) {
            // Si el valor total actual no es el mismo que el valor en la fila anterior, significa que se incluyó el ítem actual
            if (valorTotal != dp[i - 1][w]) {
                seleccionados[i - 1] = true;
                pesoTotal += items[i - 1].getPeso();
                valorTotal -= items[i - 1].getValor();
                w -= items[i - 1].getPeso();
            }
        }

        // Imprimir el valor total y el peso total en la etapa actual
        System.out.println("Valor total: " + dp[etapa][capacidad]);
        System.out.println("Peso total: " + pesoTotal);

        // Imprimir los nombres de los ítems seleccionados en la etapa actual
        System.out.print("Items seleccionados: ");
        for (int i = 0; i < seleccionados.length; i++) {
            if (seleccionados[i]) {
                System.out.print(items[i].getNombre() + " ");
            }
        }
        System.out.println();
        System.out.println();
    }
}

