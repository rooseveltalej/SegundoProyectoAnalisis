//Fecha de creación: 10/05/2024
public class Item {
    private int peso;
    private int valor;

    public Item(int peso, int valor) {
        this.peso = peso;
        this.valor = valor;
    }

    public int getPeso() {
        return peso;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Item{" + "peso=" + peso + ", valor=" + valor + '}';
    }

}
