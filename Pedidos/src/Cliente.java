import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private List<Pedido> pedidos;  // Relación: un cliente tiene varios pedidos

    public Cliente(String nombre, String email) {
        super(nombre, email);
        this.pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    // En Cliente.java
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }


    @Override
    public String toString() {
        return "Cliente: " + getNombre() + " (" + getEmail() + ")";
    }
}
