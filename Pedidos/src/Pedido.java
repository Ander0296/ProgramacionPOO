import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private List<Producto> productos;

    public Pedido(int id) {
        this.id = id;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public double calcularTotal() {
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public String toString() {
        StringBuilder detalle = new StringBuilder("Pedido #" + id + "\n");
        for (Producto p : productos) {
            detalle.append("   - ").append(p.getNombre())
                    .append(" $").append(p.getPrecio()).append("\n");
        }
        detalle.append("Total: $").append(calcularTotal());
        return detalle.toString();
    }
}
