// Fecha de creación: 10/05/2024
public class Dinamico {
    public static Mochila resolverMochilaDP(Mochila mochila) {
        int capacidad = mochila.getCapacidad();
        Item[] items = mochila.getItems();
        int n = items.length;

        int[][] dp = new int[n + 1][capacidad + 1];

        // Llenar la tabla dp
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacidad; w++) {
                if (items[i - 1].getPeso() <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w],
                                        dp[i - 1][w - items[i - 1].getPeso()] + items[i - 1].getValor());
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
            // Imprimir los resultados cada 5 etapas
            if (i % 5 == 0 || i == n) {
                imprimirResultados(dp, i, items, capacidad);
            }
        }

        // Encontrar los items seleccionados
        int w = capacidad;
        int valorTotal = dp[n][w];
        int pesoTotal = 0;
        boolean[] seleccionados = new boolean[n];
        
        for (int i = n; i > 0 && valorTotal > 0; i--) {
            if (valorTotal != dp[i - 1][w]) {
                seleccionados[i - 1] = true;
                pesoTotal += items[i - 1].getPeso();
                valorTotal -= items[i - 1].getValor();
                w -= items[i - 1].getPeso();
            }
        }

        Item[] itemsSeleccionados = java.util.stream.IntStream.range(0, n)
                                                             .filter(i -> seleccionados[i])
                                                             .mapToObj(i -> items[i])
                                                             .toArray(Item[]::new);

        Mochila resultado = new Mochila(mochila.getCapacidad(), itemsSeleccionados);
        resultado.setPesoTotal(pesoTotal);
        resultado.setValorTotal(dp[n][capacidad]);

        return resultado;
    }

    private static void imprimirResultados(int[][] dp, int etapa, Item[] items, int capacidad) {
        System.out.println("Resultados en la etapa " + etapa + ":");
        int valorTotal = dp[etapa][capacidad];
        int pesoTotal = 0;
        boolean[] seleccionados = new boolean[etapa];
        
        int w = capacidad;
        for (int i = etapa; i > 0 && valorTotal > 0; i--) {
            if (valorTotal != dp[i - 1][w]) {
                seleccionados[i - 1] = true;
                pesoTotal += items[i - 1].getPeso();
                valorTotal -= items[i - 1].getValor();
                w -= items[i - 1].getPeso();
            }
        }

        System.out.println("Valor total: " + dp[etapa][capacidad]);
        System.out.println("Peso total: " + pesoTotal);

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
