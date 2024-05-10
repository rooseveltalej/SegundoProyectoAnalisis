//Fecha de creación: 10/05/2024
public class Mochila {
    private int capacidad;
    private Item[] items;
    private int pesoTotal;
    private int valorTotal;

    public Mochila(int capacidad, Item[] items) {
        this.capacidad = capacidad;
        this.items = items;
        this.pesoTotal = 0;
        this.valorTotal = 0;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public Item[] getItems() {
        return items;
    }

    public int getPesoTotal() {
        return pesoTotal;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public void setPesoTotal(int pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Mochila{" + "capacidad=" + capacidad + ", items=" + items + ", pesoTotal=" + pesoTotal + ", valorTotal=" + valorTotal + '}';
    }


}
