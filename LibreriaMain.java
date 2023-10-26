/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package actividad07.libreria;

import gestorBBDD.Autor;

import gestorBBDD.Libro;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Miguel de la Rubia
 */
public class LibreriaMain {

   private static Connection con;
   private static Scanner sc = new Scanner(System.in);
   private Statement stmt;
   
   private static String datosConexion = "jdbc:mysql://127.0.0.1:3307/";
   private static String baseDatos = "Libreria";
   private static String usuario = "root";
   private static String password = "";
   
   
   public static void main(String[] args) throws SQLException, Exception {
        //BBDD_Gestor bbdd = new BBDD_Gestor();
        //MENU
        
        conectarBBDD();
        
        int opcion;
        
        do{
            System.out.println("\nQué quieres hacer?");
            System.out.println("1. restablecer la base de datos");
            System.out.println("2. mostrar autores");
            System.out.println("3. mostrar libros");
            System.out.println("4. modificar un autor");
            System.out.println("5. eliminar un libro");
            System.out.println("0. Salir");
        //titulo, autor, pais, paginas, genero
            opcion =sc.nextInt();
            
            switch (opcion) {
                case 1:
                    reestablecerBBDD();
                    crearBBDD();
                    crearTablaLibro();
                    crearTablaAutor();
                    insertarAutor("Gabriel","García Márquez");
                    insertarAutor("Isabel","Allende");
                    insertarLibro("Cien años de soledad","Gabriel García Márquez","Colombia",471,"Novela");
                    insertarLibro("La casa de los espíritus","Isabel Allende","Chile",650,"Novela");
                    break;
                case 2:
                   
                    ArrayList<Autor> autores = listaAutores();
                    mostrarAutores(autores);
                   
                    break;
                case 3:
                    
                    ArrayList<Libro> lista = listaLibros();
                    mostrarListaLibros(lista);
                    
                    break;
                case 4:
                    modificarAutor();
                    
                    break;
                case 5 :
                    
                    eliminarLibro();
                    break;
                    
                case 0 :
                    System.out.println("Adiós");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        
        
        
        }while(opcion!=0);
        
        desconectarBaseDatos();

        }
    private static void conectarBBDD(){
        
        try{
            
            con = DriverManager.getConnection(datosConexion+"?useSSL=true", usuario, password);
            System.out.println("Conectado a la base de datos.");
        }catch (SQLException e){
            System.out.println("No se ha podido conectar a la base de datos");
            e.printStackTrace();
        }
    }
    
    private static void desconectarBaseDatos() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Desconectado de la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("No ha sido posible desconectarse de la base de datos.");
            e.printStackTrace();
        }
    }
    
    private static void reestablecerBBDD() throws SQLException{
       try{
        Statement stmt;
        stmt = con.createStatement();
        String query = "DROP DATABASE IF EXISTS Libreria";
        stmt.executeUpdate(query); 
        System.out.println("Base de datos reestablecida");
       }catch(SQLException e){
        e.printStackTrace();
        System.out.println("No existía ninguna base de datos previamente con el nombre Libreria");
       }
    
    }
    private static void crearBBDD() throws Exception{
        //creamos un String que contendrá el código SQL a ejecutar. Se crea un objeto
        //Statement con el constructor “createStatement” y luego llamamos al método
        //“executeUpdate” pasándole como parámetro el String con el código SQL.
        
        String query = "create database if not exists "+ baseDatos + ";";
        Statement stmt = null;
        
        try{
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con = DriverManager.getConnection(datosConexion+"?useSSL=true", usuario, password);
            System.out.println("Se ha creado correctamente la base de datos Libreria");    
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error al crear la base de datos");
            
        }finally{ //En el bloque finally hacemos una llamada al método “close” del objeto Statement,
                    //liberando los recursos utilizados para ejecutar el código SQL en la base de datos.
            stmt.close();
        }
    }
    
    private static void crearTablaLibro() throws Exception {
        String query = "create table if not exists Libro("+ "codigo int primary key auto_increment, "+ "titulo varchar(100), "+ "autor varchar(50), "+ "pais varchar (50), "+ "paginas int, "+ "genero varchar (50));";
                                                
        Statement stmt = null;
        
        try{
            con.setCatalog(baseDatos);//seleccionar la base de datos
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Se ha creado la Tabla Libro");
        }catch (SQLException e){
            
            e.printStackTrace();
            System.out.println("Error al crear la Tabla Libro");
        }finally{       
            stmt.close();
        }
    }
    
    private static void crearTablaAutor() throws Exception {
        String query = "create table if not exists Autor("+ "codigo int primary key auto_increment, "+ "nombre varchar(50), "+ "apellido varchar(50));";
                                                
        Statement stmt = null;
        
        try{
            con.setCatalog(baseDatos);//seleccionar la base de datos
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Se ha creado la Tabla Autor");
        }catch (SQLException e){
            
            e.printStackTrace();
            System.out.println("Error al crear la Tabla Autor");
        }finally{       
            stmt.close();
        }
    }
    
    private static void insertarLibro(String titulo, String autor, String pais, int paginas, String genero) throws SQLException{
        Statement stmt= con.createStatement();
        String query = "INSERT INTO LIBRO(titulo, autor, pais, paginas, genero)"
                        + "VALUES('" + titulo + "', '" + autor +"', '" + pais + "', " + paginas + ", '"+ genero +"')"; 
        stmt.executeUpdate(query);
        System.out.println("Insertado Libro"+query);
    }
    
    private static void insertarAutor(String nombre, String apellido) throws SQLException{
        Statement stmt= con.createStatement();
        String query = "INSERT INTO AUTOR(nombre, apellido)"
                        + "VALUES('" + nombre + "', '" + apellido +"')";
        stmt.executeUpdate(query);
        System.out.println("Insertado Autor"+ query);
    }
    
    private static ArrayList<Libro> listaLibros() throws SQLException {
    
            String query = "select * from Libro";//select all de la tabla libro
            Statement stmt=null;
            ResultSet rs =null;//lo necesitamos para tratar los diferentes datos
            ArrayList<Libro>libros=new ArrayList<Libro>();
            
            Libro l;
            
            try{
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            
                while(rs.next()){
                    
                    l =new Libro(rs.getInt("codigo"),rs.getString("titulo"), rs.getString("autor"),rs.getString("pais"),rs.getInt("paginas"),rs.getString("genero"));
                                      
                    libros.add(l);//almacenamos los datos en el ArrayList "libros"
                }
                   
            }catch(SQLException e){
                e.printStackTrace(); 
            } finally {
            
                rs.close();
                stmt.close();
            }
            
           return libros;          
    }
    
    private static void mostrarListaLibros(ArrayList<Libro> lista) {
    System.out.println("Lista de libros:");
    for (Libro libro : lista) {
        System.out.println("Código: " + libro.getCodigo());
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("País: " + libro.getPais());
        System.out.println("Páginas: " + libro.getPaginas());
        System.out.println("Género: " + libro.getGenero());
        System.out.println("----------------------");
        }
    }
    
    private static ArrayList<Autor> listaAutores() throws SQLException {
    
            String query = "select * from Autor";//select all de la tabla libro
            Statement stmt=null;
            ResultSet rs =null;//lo necesitamos para tratar los diferentes datos
            ArrayList<Autor>autores=new ArrayList<Autor>();
            
            Autor a;
            
            try{
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            
                while(rs.next()){
                    
                    a =new Autor(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("apellido"));
                                      
                    autores.add(a);//almacenamos los datos en el ArrayList "libros"
                }
                   
            }catch(SQLException e){
                e.printStackTrace(); 
            } finally {
            
                rs.close();
                stmt.close();
            }
            
           return autores; 
            
            
    }
    
    private static void mostrarAutores(ArrayList<Autor> autores) {
    for (Autor autor : autores) {
        System.out.println("Código: " + autor.getCodigo() + ", Nombre: " + autor.getNombre() + ", Apellido: " + autor.getApellido());
        }
    }

    private static void modificarAutor() throws SQLException {
    
        ArrayList<Autor> autores = listaAutores(); // obtenemos la lista de autores
    Scanner sc = new Scanner(System.in);

    // Mostramos los autores
    System.out.println("Listado de Autores:");
    for (Autor a : autores) {
        System.out.println(a.getCodigo() + ". " + a.getNombre() + " " + a.getApellido());
    }

    // Pedimos al usuario el ID del autor a modificar
    System.out.print("Ingrese el ID del autor a modificar: ");
    int idAutor = sc.nextInt();

    // Obtenemos el autor a modificar
    Autor autorModificar = null;
    for (Autor a : autores) {
        if (a.getCodigo() == idAutor) {
            autorModificar = a;
            break;
        }
    }

    if (autorModificar != null) {
        // Pedimos al usuario el dato a modificar
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.print("Ingrese el número del dato a modificar: ");
        int datoModificar = sc.nextInt();

        // Actualizamos el dato en la base de datos
        String campo = "";
        String nuevoValor = "";
        switch (datoModificar) {
            case 1:
                campo = "nombre";
                System.out.print("Ingrese el nuevo nombre: ");
                nuevoValor = sc.next();
                break;
            case 2:
                campo = "apellido";
                System.out.print("Ingrese el nuevo apellido: ");
                nuevoValor = sc.next();
                break;
            default:
                System.out.println("Opción inválida");
                return;
        }

        String query = "UPDATE Autor SET " + campo + " = ? WHERE codigo = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, nuevoValor);
        stmt.setInt(2, autorModificar.getCodigo());
        int filasModificadas = stmt.executeUpdate();

        if (filasModificadas > 0) {
                System.out.println("Autor modificado exitosamente");
            } else {
                System.out.println("No se pudo modificar el autor");
            }
        } else {
            System.out.println("No se encontró el autor con ese ID");
        }
    }
    
    private static void eliminarLibro() throws SQLException {
    
        Scanner sc = new Scanner(System.in);
    System.out.println("Listado de libros:");
    ArrayList<Libro> libros = listaLibros();
    for (Libro l : libros) {
        System.out.println(l.getCodigo() + " - " + l.getTitulo());
    }
    System.out.print("Seleccione el código del libro que desea eliminar: ");
    int codigo = sc.nextInt();
    String query = "DELETE FROM Libro WHERE codigo = " + codigo;
    Statement stmt = null;
    try {
            stmt = con.createStatement();
            int filasAfectadas = stmt.executeUpdate(query);
            if (filasAfectadas > 0) {
                System.out.println("Libro eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el libro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

}

    

