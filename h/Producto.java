package modelo;

public class Producto implements Comparable<Producto> {
    private int id;
    private String nombre;
    private String lote;
    private String categoria;
    private int cantidad;

    public Producto(int id, String nombre, String lote, String categoria, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.lote = lote;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public int getKey() {
        return id;
    }

    public String toString() {
        return "Producto{id=" + id + ", nombre=" + nombre + ", lote=" + lote + ", categoria=" + categoria + ", cantidad=" + cantidad + "}";
    }
    
    public int compareTo(Producto o) {
        return Integer.compare(this.getKey(), o.getKey()); 
    }
}

