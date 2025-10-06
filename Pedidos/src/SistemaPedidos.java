import java.util.ArrayList;
import java.util.List;

public class SistemaPedidos {
    private static SistemaPedidos instancia;
    private List<Cliente> clientes;
    private List<Producto> productos;

    // Constructor privado
    private SistemaPedidos() {
        clientes = new ArrayList<>();
        productos = new ArrayList<>();
    }

    // Singleton
    public static SistemaPedidos getInstancia() {
        if (instancia == null) {
            instancia = new SistemaPedidos();
        }
        return instancia;
    }

    // ======================
    // CLIENTES
    // ======================
    public void agregarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getEmail()) != null) {
            System.out.println("⚠️ El cliente con email " + cliente.getEmail() + " ya existe.");
        } else {
            clientes.add(cliente);
            System.out.println("✅ Cliente agregado.");
        }
    }

    public Cliente buscarCliente(String email) {
        return clientes.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarCliente(String email) {
        Cliente cliente = buscarCliente(email);
        if (cliente != null) {
            clientes.remove(cliente);
            return true;
        }
        return false;
    }

    public boolean editarCliente(String email, String nuevoNombre, String nuevoEmail) {
        Cliente cliente = buscarCliente(email);
        if (cliente != null) {
            cliente.setNombre(nuevoNombre);
            cliente.setEmail(nuevoEmail);
            return true;
        }
        return false;
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("⚠️ No hay clientes registrados.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    // ======================
    // PRODUCTOS
    // ======================
    public void agregarProducto(Producto producto) {
        if (buscarProducto(producto.getNombre()) != null) {
            System.out.println("⚠️ El producto '" + producto.getNombre() + "' ya existe.");
        } else {
            productos.add(producto);
            System.out.println("✅ Producto agregado.");
        }
    }

    public Producto buscarProducto(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarProducto(String nombre) {
        Producto producto = buscarProducto(nombre);
        if (producto != null) {
            productos.remove(producto);
            return true;
        }
        return false;
    }

    public boolean editarProducto(String nombre, String nuevoNombre, double nuevoPrecio) {
        Producto producto = buscarProducto(nombre);
        if (producto != null) {
            producto.setNombre(nuevoNombre);
            producto.setPrecio(nuevoPrecio);
            return true;
        }
        return false;
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("⚠️ No hay productos registrados.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    // ======================
    // PEDIDOS
    // ======================
    public void mostrarPedidosCliente(String email) {
        Cliente cliente = buscarCliente(email);
        if (cliente != null) {
            if (cliente.getPedidos().isEmpty()) {
                System.out.println("⚠️ Este cliente no tiene pedidos.");
            } else {
                cliente.getPedidos().forEach(System.out::println);
            }
        } else {
            System.out.println("⚠️ Cliente no encontrado.");
        }
    }
}
