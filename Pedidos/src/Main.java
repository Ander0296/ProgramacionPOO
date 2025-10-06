import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaPedidos sistema = SistemaPedidos.getInstancia();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int idPedido = 1;

        while (!salir) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar producto");
            System.out.println("3. Crear pedido");
            System.out.println("4. Ver pedidos de un cliente");
            System.out.println("5. Listar clientes");
            System.out.println("6. Listar productos");
            System.out.println("7. Editar cliente");
            System.out.println("8. Eliminar cliente");
            System.out.println("9. Editar producto");
            System.out.println("10. Eliminar producto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese nombre del cliente: ");
                        String nombre = sc.nextLine();
                        System.out.print("Ingrese email del cliente: ");
                        String email = sc.nextLine();
                        Cliente cliente = new Cliente(nombre, email);
                        sistema.agregarCliente(cliente);
                        break;

                    case 2:
                        System.out.print("Ingrese nombre del producto: ");
                        String nombreProd = sc.nextLine();
                        System.out.print("Ingrese precio del producto: ");
                        double precio = Double.parseDouble(sc.nextLine());

                        Producto producto = new Producto(nombreProd, precio);
                        sistema.agregarProducto(producto);
                        break;

                    case 3:
                        System.out.print("Ingrese email del cliente: ");
                        String emailCliente = sc.nextLine();
                        Cliente c = sistema.buscarCliente(emailCliente);

                        if (c != null) {
                            Pedido pedido = new Pedido(idPedido++);
                            boolean agregarMas = true;

                            while (agregarMas) {
                                System.out.print("Ingrese nombre del producto a agregar: ");
                                String prodNom = sc.nextLine();

                                Producto prodExistente = sistema.buscarProducto(prodNom);
                                if (prodExistente != null) {
                                    pedido.agregarProducto(prodExistente);
                                    System.out.println("✅ Producto agregado: " + prodExistente);
                                } else {
                                    System.out.println("⚠️ El producto no existe. Debe estar registrado antes.");
                                }

                                System.out.print("¿Agregar otro producto? (s/n): ");
                                String respuesta = sc.nextLine();
                                agregarMas = respuesta.equalsIgnoreCase("s");
                            }

                            if (!pedido.getProductos().isEmpty()) {
                                c.agregarPedido(pedido);
                                System.out.println("✅ Pedido creado:\n" + pedido);
                            } else {
                                System.out.println("⚠️ No se creó el pedido porque no tenía productos válidos.");
                            }

                        } else {
                            System.out.println("⚠️ Cliente no encontrado.");
                        }
                        break;

                    case 4:
                        System.out.print("Ingrese email del cliente: ");
                        String emailBuscar = sc.nextLine();
                        sistema.mostrarPedidosCliente(emailBuscar);
                        break;

                    case 5:
                        sistema.listarClientes();
                        break;

                    case 6:
                        sistema.listarProductos();
                        break;

                    case 7:
                        System.out.print("Ingrese email del cliente a editar: ");
                        String emailEdit = sc.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = sc.nextLine();
                        System.out.print("Nuevo email: ");
                        String nuevoEmail = sc.nextLine();

                        if (sistema.editarCliente(emailEdit, nuevoNombre, nuevoEmail)) {
                            System.out.println("✅ Cliente actualizado.");
                        } else {
                            System.out.println("⚠️ Cliente no encontrado.");
                        }
                        break;

                    case 8:
                        System.out.print("Ingrese email del cliente a eliminar: ");
                        String emailDel = sc.nextLine();

                        if (sistema.eliminarCliente(emailDel)) {
                            System.out.println("✅ Cliente eliminado.");
                        } else {
                            System.out.println("⚠️ Cliente no encontrado.");
                        }
                        break;

                    case 9:
                        System.out.print("Ingrese nombre del producto a editar: ");
                        String prodEdit = sc.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String nuevoNomProd = sc.nextLine();
                        System.out.print("Nuevo precio: ");
                        double nuevoPrecio = Double.parseDouble(sc.nextLine());

                        if (sistema.editarProducto(prodEdit, nuevoNomProd, nuevoPrecio)) {
                            System.out.println("✅ Producto actualizado.");
                        } else {
                            System.out.println("⚠️ Producto no encontrado.");
                        }
                        break;

                    case 10:
                        System.out.print("Ingrese nombre del producto a eliminar: ");
                        String prodDel = sc.nextLine();

                        if (sistema.eliminarProducto(prodDel)) {
                            System.out.println("✅ Producto eliminado.");
                        } else {
                            System.out.println("⚠️ Producto no encontrado.");
                        }
                        break;

                    case 0:
                        salir = true;
                        System.out.println("👋 Gracias por usar el sistema.");
                        break;

                    default:
                        System.out.println("⚠️ Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: debe ingresar un número.");
            } catch (Exception e) {
                System.out.println("⚠️ Error inesperado: " + e.getMessage());
            }
        }

        sc.close();
    }
}