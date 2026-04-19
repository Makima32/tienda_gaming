package videojuegos;

public class Videojuego {

    int id;
    String titulo;
    String genero;
    double precio;
    Pegi pegi;
    int stock;
    int oferta; // EN porcentaje

    public enum Pegi {
        PEGI_0, PEGI_12, PEGI_16, PEGI_18;
    }

    public Videojuego(String titulo, String genero, double precio, int stock, int oferta, Pegi pegi) {
        this.titulo = titulo;
        this.genero = genero;
        this.precio = precio;
        this.stock = stock;
        this.oferta = oferta;
        this.pegi = pegi;
    }

    public Videojuego() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Pegi getPegi() {
        return pegi;
    }

    public void setPegi(Pegi pegi) {
        this.pegi = pegi;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getOferta() {
        return oferta;
    }

    public void setOferta(int oferta) {
        this.oferta = oferta;
    }

    @Override
    public String toString() {
        return " id: " + id + ", titulo: " + titulo + ", genero: " + genero + ", precio: " + precio + "$, " + "pegi: "
                + pegi + ", stock: " + stock + ", oferta: " + oferta + "%" + "\n ";
    }



}
