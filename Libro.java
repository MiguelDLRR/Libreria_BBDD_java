/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorBBDD;

/**
 *titulo, autor, pais, paginas, genero
 * @author Miguel de la Rubia
 */
public class Libro {

    @Override
    public String toString() {
        return "Libro{" + "codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", pais=" + pais + ", paginas=" + paginas + ", genero=" + genero + '}';
    }

    
    int codigo;
    String titulo;
    String autor;
    String pais;
    int paginas;
    String genero;
    
    //constructor de la clase
    public Libro(int codigo, String titulo, String autor, String pais, int paginas, String genero) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.pais = pais;
        this.paginas = paginas;
        this.genero = genero;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
