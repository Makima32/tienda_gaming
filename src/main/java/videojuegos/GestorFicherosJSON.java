package videojuegos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GestorFicherosJSON {

    private Gson gson;
    private String rutaArchivo = "videojuegos.json";

    public GestorFicherosJSON() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public ArrayList<Videojuego> leerDatos() {
        try {
            FileReader lector = new FileReader(rutaArchivo);
            Type tipoLista = new TypeToken<ArrayList<Videojuego>>() {
            }.getType();
            ArrayList<Videojuego> listaLeida = gson.fromJson(lector, tipoLista);
            lector.close();

            if (listaLeida == null)
                return new ArrayList<>();
            return listaLeida;

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardarDatos(ArrayList<Videojuego> listaActualizada) {
        try {
            FileWriter escritor = new FileWriter(rutaArchivo);
            gson.toJson(listaActualizada, escritor);
            escritor.close();
        } catch (Exception e) {
            System.out.println("Error al guardar en el JSON.");
        }
    }
}