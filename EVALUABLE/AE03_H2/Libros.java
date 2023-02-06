package AE03_H2;

public class Libros {
    static int id_auto=0;
    private int id;
    private String titulo;

    public Libros(int id,String titulo) {
        this.id=id;
        this.titulo = titulo;
        
        if(id>id_auto) {
            id_auto=id;
        }
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
