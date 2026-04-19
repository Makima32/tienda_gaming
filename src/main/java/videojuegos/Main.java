package videojuegos;

import java.util.ArrayList;
import java.util.Scanner;
import videojuegos.Videojuego.Pegi;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        VideojuegosDao videojuegosDao = new VideojuegosDao();

        // System.out.println("Cargando datos de prueba");

        // videojuegosDao.insertarVideojuego(new Videojuego("Overwatch", "Hero Shooter",
        // 0.0, 500, 0, Pegi.PEGI_12));
        // videojuegosDao.insertarVideojuego(new Videojuego("Expedition 33", "RPG por
        // turnos", 59.99, 50, 0, Pegi.PEGI_16));
        // videojuegosDao.insertarVideojuego(new Videojuego("League of Legends", "MOBA",
        // 0.0, 999, 0, Pegi.PEGI_12));
        // videojuegosDao.insertarVideojuego(new Videojuego("Code Vein", "Soulslike
        // Anime", 29.99, 30, 50, Pegi.PEGI_16));
        // videojuegosDao.insertarVideojuego(new Videojuego("Code Vein 2", "Soulslike
        // Anime", 69.99, 100, 0, Pegi.PEGI_16));
        // videojuegosDao.insertarVideojuego(new Videojuego("Zelda Tears of the
        // Kingdom", "Aventura", 69.99, 200, 0, Pegi.PEGI_12));
        // videojuegosDao.insertarVideojuego(new Videojuego("Honkai Star Rail", "RPG por
        // turnos Gacha", 0.0, 1000, 10, Pegi.PEGI_12));

        int opcion = -1;

        while (opcion != 6) {
            System.out.println("\n========================================");
            System.out.println("       GESTOR DE TIENDA GAMING ");
            System.out.println("========================================");
            System.out.println("1. Listar todos los videojuegos");
            System.out.println("2. Buscar videojuegos");
            System.out.println("3. Anadir nuevo videojuego");
            System.out.println("4. Modificar videojuego");
            System.out.println("5. Eliminar videojuego");
            System.out.println("6. Salir del programa");
            System.out.println("========================================");
            System.out.print("Elige una opcion: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        videojuegosDao.listarVideojuegos();
                        esperarEnter(scanner);
                        break;

                    case 2:
                        System.out.println("\n--- BUSCADOR ---");
                        System.out.print(
                                "¿Por que campo quieres buscar? (id, titulo, genero, precio, pegi, stock, oferta): ");
                        String campo = scanner.nextLine().toLowerCase();

                        String valor = "";

                        switch (campo) {
                            case "pegi":
                                System.out.println("   -> PEGIs validos: 3, 7, 12, 16, 18");
                                System.out.print("Introduce el PEGI a buscar: ");
                                valor = scanner.nextLine();
                                break;
                            case "genero":
                                System.out.println(
                                        "   -> Generos en la base de datos: " + videojuegosDao.obtenerGenerosUnicos());
                                System.out.print("Introduce el genero a buscar: ");
                                valor = scanner.nextLine();
                                break;
                            case "stock":
                                System.out.println(
                                        "   -> Escribe '0' para ver los juegos SIN stock, o un numero para ver una cantidad exacta.");
                                System.out.print("Introduce el stock a buscar: ");
                                valor = scanner.nextLine();
                                break;
                            case "oferta":
                                System.out.println(
                                        "   -> Escribe '0' para ver juegos SIN oferta, o el % exacto que buscas.");
                                System.out.print("Introduce la oferta a buscar: ");
                                valor = scanner.nextLine();
                                break;
                            case "precio":
                                System.out.println(
                                        "   -> Introduce el precio exacto (ej: 59.99). (La busqueda por rangos no esta disponible en esta version).");
                                System.out.print("Introduce el precio a buscar: ");
                                valor = scanner.nextLine();
                                break;
                            default:
                                System.out.print("Introduce el valor a buscar: ");
                                valor = scanner.nextLine();
                                break;
                        }

                        ArrayList<Videojuego> resultados = videojuegosDao.buscarVideojuegos(valor, campo);

                        if (resultados.isEmpty()) {
                            System.out.println("\n[!] No se ha encontrado ningun juego que coincida.");
                        } else {
                            System.out.println("\n[OK] Se han encontrado " + resultados.size() + " resultado(s):");
                            for (Videojuego v : resultados) {
                                System.out.println(v);
                            }
                        }
                        esperarEnter(scanner);
                        break;

                    case 3:
                        System.out.println("\n--- AÑADIR JUEGO ---");
                        System.out.print("Titulo: ");
                        String tituloNuevo = scanner.nextLine();
                        System.out.print("Genero: ");
                        String generoNuevo = scanner.nextLine();
                        System.out.print("Precio (usa coma para decimales, ej: 59,99): ");
                        double precioNuevo = scanner.nextDouble();
                        System.out.print("Stock: ");
                        int stockNuevo = scanner.nextInt();
                        System.out.print("Oferta (%): ");
                        int ofertaNueva = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("PEGI (3, 7, 12, 16, 18): ");
                        String pegiStr = scanner.nextLine();
                        Pegi pegiNuevo = Pegi.valueOf("PEGI_" + pegiStr);

                        Videojuego nuevoJuego = new Videojuego(tituloNuevo, generoNuevo, precioNuevo, stockNuevo,
                                ofertaNueva, pegiNuevo);
                        videojuegosDao.insertarVideojuego(nuevoJuego);

                        esperarEnter(scanner);
                        break;

                    case 4:
                        System.out.println("\n--- MODIFICAR JUEGO ---");
                        System.out.print("Cual es el ID del juego a modificar: ");
                        int idMod = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("¿Que campo quieres cambiar? (titulo, genero, precio, pegi, stock, oferta): ");
                        String campoMod = scanner.nextLine();
                        System.out.print("Escribe el nuevo valor (Si es PEGI, escribe PEGI_18, PEGI_12...): ");
                        String valorMod = scanner.nextLine();

                        videojuegosDao.actualizarVideojuego(idMod, campoMod, valorMod);
                        esperarEnter(scanner);
                        break;

                    case 5:
                        System.out.println("\n--- ELIMINAR JUEGO ---");
                        System.out.println("--- Lista rapida de juegos ---");
                        videojuegosDao.listarVideojuegos();

                        System.out.print("\n¿Que juego quieres eliminar? Escribe su ID: ");
                        int idEliminar = scanner.nextInt();
                        scanner.nextLine();

                        videojuegosDao.eliminarVideojuego(idEliminar);
                        esperarEnter(scanner);
                        break;

                    case 6:
                        System.out.println("Saliendo y guardando datos....");
                        break;

                    default:
                        System.out.println(" Opcion no valida. Por favor, elige un numero del 1 al 6.");
                        esperarEnter(scanner);
                }
            } catch (Exception e) {
                System.out.println(
                        " Error de formato. Has introducido texto donde iba un numero, o te equivocaste en el PEGI.");
                scanner.nextLine();
                esperarEnter(scanner);
            }
        }

        scanner.close();
    }

    public static void esperarEnter(Scanner scanner) {
        System.out.print("\nPresiona ENTER para continuar...");
        scanner.nextLine();
    }
}