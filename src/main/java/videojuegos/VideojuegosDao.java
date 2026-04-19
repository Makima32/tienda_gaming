package videojuegos;

import java.util.ArrayList;

public class VideojuegosDao {

    ArrayList<Videojuego> videojuegos = new ArrayList<>();
    
    GestorFicherosJSON gestorFicheros = new GestorFicherosJSON();

    public VideojuegosDao() {
        videojuegos = gestorFicheros.leerDatos();
    }

    public void listarVideojuegos() {
        System.out.println("-----------Lista de videojuegos-----------");
        try {
            for (int i = 0; i < videojuegos.size(); i++) {
                System.out.println(videojuegos.get(i));
            }
        } catch (Exception e) {
            System.out.println("Error al listar videojuegos");
        }
    }

  public void insertarVideojuego(Videojuego videojuego) {
    try {
        int maxId = 0;
        for (Videojuego v : videojuegos) {
            if (v.getId() > maxId) {
                maxId = v.getId();
            }
        }
        videojuego.setId(maxId + 1);
        videojuegos.add(videojuego);
        
        gestorFicheros.guardarDatos(videojuegos);
        System.out.println("[OK] Juego insertado con ID: " + videojuego.getId());
    } catch (Exception e) {
        System.out.println("[!] Error al insertar.");
    }
}

    public ArrayList<Videojuego> buscarVideojuegos(String valorBusqueda, String campo) {
        ArrayList<Videojuego> filtrados = new ArrayList<>();
        String valor = valorBusqueda.toLowerCase();

        for (Videojuego v : videojuegos) {
            boolean coincide = false;

            switch (campo.toLowerCase()) {
                case "id":
                    if (String.valueOf(v.getId()).equals(valor)) coincide = true;
                    break;
                case "titulo":
                    if (v.getTitulo().toLowerCase().contains(valor)) coincide = true;
                    break;
                case "genero":
                    if (v.getGenero().toLowerCase().contains(valor)) coincide = true;
                    break;
                case "precio":
                    if (String.valueOf(v.getPrecio()).contains(valor)) coincide = true;
                    break;
                case "pegi":
                    if (v.getPegi().toString().toLowerCase().contains(valor)) coincide = true;
                    break;
                case "stock":
                    if (String.valueOf(v.getStock()).equals(valor)) coincide = true;
                    break;
                case "oferta":
                    if (String.valueOf(v.getOferta()).contains(valor)) coincide = true;
                    break;
            }

            if (coincide) {
                filtrados.add(v);
            }
        }
        return filtrados;
    }

    public void actualizarVideojuego(int id, String campo, String nuevoValor) {
        boolean encontrado = false;

        for (Videojuego v : videojuegos) {
            if (v.getId() == id) {
                encontrado = true;
                
                try {
                    switch (campo.toLowerCase()) {
                        case "titulo":
                            v.setTitulo(nuevoValor);
                            System.out.println("Título actualizado correctamente.");
                            break;
                        case "genero":
                            v.setGenero(nuevoValor);
                            System.out.println("Género actualizado correctamente.");
                            break;
                        case "precio":
                            v.setPrecio(Double.parseDouble(nuevoValor));
                            System.out.println("Precio actualizado correctamente.");
                            break;
                        case "pegi":
                            v.setPegi(Videojuego.Pegi.valueOf(nuevoValor.toUpperCase()));
                            System.out.println("PEGI actualizado correctamente.");
                            break;
                        case "stock":
                            v.setStock(Integer.parseInt(nuevoValor));
                            System.out.println("Stock actualizado correctamente.");
                            break;
                        case "oferta":
                            v.setOferta(Integer.parseInt(nuevoValor));
                            System.out.println("Oferta actualizada correctamente.");
                            break;
                        default:
                            System.out.println("Error: El campo '" + campo + "' no existe.");
                    }
                    
                    gestorFicheros.guardarDatos(videojuegos);
                    System.out.println("Cambios guardados en el JSON.");

                } catch (Exception e) {
                    System.out.println("Error al actualizar: Formato incorrecto para ese campo.");
                }
                
                break; 
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún videojuego con el ID: " + id);
        }
    }

    public void eliminarVideojuego(int id) {
        try {
            for (int i = 0; i < videojuegos.size(); i++) {
                if (videojuegos.get(i).getId() == id) {
                    videojuegos.remove(i);
                    
                    gestorFicheros.guardarDatos(videojuegos);
                    System.out.println("Juego eliminado y JSON actualizado.");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar videojuego");
        }
    }


    public ArrayList<String> obtenerGenerosUnicos() {
        ArrayList<String> generos = new ArrayList<>();
        
        for (Videojuego v : videojuegos) {
            String generoActual = v.getGenero();
            if (!generos.contains(generoActual)) {
                generos.add(generoActual);
            }
        }
        return generos;
    }
}