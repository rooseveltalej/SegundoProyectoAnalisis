//Fecha de creación: 10/05/2024
//Fecha de creación: 10/05/2024
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

        Item[] itemsSeleccionados = java.util.Arrays.stream(items)
                                                    .filter(item -> seleccionados[java.util.Arrays.asList(items).indexOf(item)])
                                                    .toArray(Item[]::new);

        Mochila resultado = new Mochila(mochila.getCapacidad(), itemsSeleccionados);
        resultado.setPesoTotal(pesoTotal);
        resultado.setValorTotal(dp[n][capacidad]);

        return resultado;
    }
}
