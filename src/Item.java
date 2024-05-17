//Fecha de creación: 10/05/2024
public class Item {
    private String nombre;
    private int peso;
    private int valor;

    public Item(String nombre, int peso, int valor) {
        this.nombre = nombre;
        this.peso = peso;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPeso() {
        return peso;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Item{"+ "nombre=" + nombre + "peso=" + peso + ", valor=" + valor + '}';
    }

}
