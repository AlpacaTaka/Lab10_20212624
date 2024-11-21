package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;

import java.sql.*;
import java.util.ArrayList;

public class peliculaDao extends baseDao{

    public ArrayList<pelicula> listarPeliculas() {

        ArrayList<pelicula> listaPeliculas = new ArrayList<>();



        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                    "(SELECT * FROM PELICULA ) AS A \n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM GENERO) AS B\n" +
                    "ON A.IDGENERO = B.IDGENERO\n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM STREAMING) AS C\n" +
                    "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                    "ORDER BY RATING DESC , BOXOFFICE DESC";


            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                pelicula movie = new pelicula();
                genero genero = new genero();
                streaming streaming = new streaming();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
                String titulo = rs.getString("titulo");
                movie.setTitulo(titulo);
                String director = rs.getString("director");
                movie.setDirector(director);
                int anoPublicacion = rs.getInt("anoPublicacion");
                movie.setAnoPublicacion(anoPublicacion);
                double rating = rs.getDouble("rating");
                movie.setRating(rating);
                double boxOffice = rs.getDouble("boxOffice");
                movie.setBoxOffice(boxOffice);
                int idGenero = rs.getInt("idGenero");
                String nombreGenero = rs.getString("nombre");

                genero.setIdGenero(idGenero);
                genero.setNombre(nombreGenero);
                movie.setGenero(genero);

                int idStreaming = rs.getInt("idStreaming");
                streaming.setIdStreaming(idStreaming);
                String nombreStreaming = rs.getString("nombreServicio");
                streaming.setNombreServicio(nombreStreaming);
                movie.setStreaming(streaming);


                String duracion = rs.getString("duracion");
                movie.setDuracion(duracion);

                boolean premioOscar = rs.getBoolean("premioOscar");
                movie.setPremioOscar(premioOscar);

                //boolean validador= validarBorrado(movie);
                //movie.setValidadorBorrado(validador);

                listaPeliculas.add(movie);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }

    public ArrayList<pelicula> listarPeliculasFiltradas(int idGenero, int idStreaming) {

        ArrayList<pelicula> listaPeliculasFiltradas= new ArrayList<>();

        if(idGenero==0){
            String sql = "SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                    "(SELECT * FROM PELICULA ) AS A \n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM GENERO) AS B\n" +
                    "ON A.IDGENERO = B.IDGENERO\n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM STREAMING) AS C\n" +
                    "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                    "ORDER BY RATING DESC , BOXOFFICE DESC "+
                    "WHERE A.idStreaming=?";

            try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){
                stmt.setInt(1, idStreaming);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    pelicula movie = new pelicula();
                    genero genero = new genero();
                    streaming streaming = new streaming();
                    int idPelicula = rs.getInt(1);
                    movie.setIdPelicula(idPelicula);
                    movie.setTitulo(rs.getString("titulo"));
                    String director = rs.getString("director");
                    movie.setDirector(director);
                    int anoPublicacion = rs.getInt("anoPublicacion");
                    movie.setAnoPublicacion(anoPublicacion);
                    double rating = rs.getDouble("rating");
                    movie.setRating(rating);
                    double boxOffice = rs.getDouble("boxOffice");
                    movie.setBoxOffice(boxOffice);
                    int idGenero2 = rs.getInt("idGenero");
                    String nombreGenero2 = rs.getString("nombre");
                    genero.setIdGenero(idGenero2);
                    genero.setNombre(nombreGenero2);
                    movie.setGenero(genero);
                    int idStreaming2 = rs.getInt("idStreaming");
                    streaming.setIdStreaming(idStreaming2);
                    String nombreStreaming2 = rs.getString("nombreServicio");
                    streaming.setNombreServicio(nombreStreaming2);
                    movie.setStreaming(streaming);
                    String duracion2 = rs.getString("duracion");
                    movie.setDuracion(duracion2);
                    boolean premioOscar2 = rs.getBoolean("premioOscar");
                    movie.setPremioOscar(premioOscar2);
                    listaPeliculasFiltradas.add(movie);
                }

            }catch (SQLException e){
                throw new RuntimeException(e);
            }


        }
        if(idStreaming==0){
            String sql = "SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                    "(SELECT * FROM PELICULA ) AS A \n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM GENERO) AS B\n" +
                    "ON A.IDGENERO = B.IDGENERO\n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM STREAMING) AS C\n" +
                    "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                    "ORDER BY RATING DESC , BOXOFFICE DESC "+
                    "WHERE B.idGenero=?";
            try(Connection conn=getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql);){
                stmt.setInt(1, idGenero);
                ResultSet rs=stmt.executeQuery();
                while (rs.next()) {
                    pelicula movie = new pelicula();
                    genero genero = new genero();
                    streaming streaming = new streaming();
                    int idPelicula = rs.getInt(1);
                    movie.setIdPelicula(idPelicula);
                    movie.setTitulo(rs.getString("titulo"));
                    String director = rs.getString("director");
                    movie.setDirector(director);
                    int anoPublicacion = rs.getInt("anoPublicacion");
                    movie.setAnoPublicacion(anoPublicacion);
                    double rating = rs.getDouble("rating");
                    movie.setRating(rating);
                    double boxOffice = rs.getDouble("boxOffice");
                    movie.setBoxOffice(boxOffice);
                    int idGenero2 = rs.getInt("idGenero");
                    String nombreGenero2 = rs.getString("nombre");
                    genero.setIdGenero(idGenero2);
                    genero.setNombre(nombreGenero2);
                    movie.setGenero(genero);
                    int idStreaming2 = rs.getInt("idStreaming");
                    streaming.setIdStreaming(idStreaming2);
                    String nombreStreaming2 = rs.getString("nombreServicio");
                    streaming.setNombreServicio(nombreStreaming2);
                    movie.setStreaming(streaming);
                    String duracion2 = rs.getString("duracion");
                    movie.setDuracion(duracion2);
                    boolean premioOscar2 = rs.getBoolean("premioOscar");
                    movie.setPremioOscar(premioOscar2);
                    listaPeliculasFiltradas.add(movie);
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


        return listaPeliculasFiltradas;
    }

    // AGREGAR CAMPOS FALTANTES (GENERO, STREAMING)
    public void editarPelicula(int idPelicula, String titulo, String director, int anoPublicacion, double rating, double boxOffice){
        try {
            String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
            String username = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password);) {
                String sql = "UPDATE PELICULA SET titulo = ?, director = ?, anoPublicacion = ? ," +
                        "rating = ?, boxOffice = ? WHERE IDPELICULA = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, titulo);
                    pstmt.setString(2, director);
                    pstmt.setInt(3, anoPublicacion);
                    pstmt.setDouble(4, rating);
                    pstmt.setDouble(5, boxOffice);
                    pstmt.setInt(6, idPelicula);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public void borrarPelicula(int idPelicula) {

        // NOTA: PARA BORRAR UNA PELICULA CORRECTAMENTE NO OLVIDAR PRIMERO BORRARLA DE LA TABLA PROTAGONSITAS


    }


}
