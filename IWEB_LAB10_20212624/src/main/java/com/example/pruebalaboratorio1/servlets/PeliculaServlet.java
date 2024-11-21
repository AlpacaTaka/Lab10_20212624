package com.example.pruebalaboratorio1.servlets;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;
import com.example.pruebalaboratorio1.daos.listasDao;
import com.example.pruebalaboratorio1.daos.peliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "pelicula-servlet", value = "/listaPeliculas")
public class PeliculaServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        peliculaDao peliculaDao = new peliculaDao();
        listasDao listaDao = new listasDao();


        switch (action) {
            case "listar":

                ArrayList<pelicula> listaPeliculas = peliculaDao.listarPeliculas();
                ArrayList<genero> listaGeneros = listaDao.listarGeneros();
                ArrayList<streaming> listaStreaming = listaDao.listarStraming();
                request.setAttribute("listaGeneros", listaGeneros);
                request.setAttribute("listaStreaming", listaStreaming);
                request.setAttribute("listaPeliculas", listaPeliculas);
                System.out.println(listaGeneros.size());

                RequestDispatcher view = request.getRequestDispatcher("listaPeliculas.jsp");
                view.forward(request,response);
                break;

            case "borrar":

                response.sendRedirect(request.getContextPath()+"/listaPeliculas?action=listar");
                break;

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        peliculaDao peliculaDao = new peliculaDao();
        listasDao listaDao = new listasDao();
        ArrayList<genero> listaGeneros = listaDao.listarGeneros();
        ArrayList<streaming> listaStreaming = listaDao.listarStraming();

        switch (action) {


            case "filtrar":

                RequestDispatcher viewFiltro = request.getRequestDispatcher("listaPeliculas.jsp");
                String idGeneroStr =request.getParameter("genero");
                int idGenero =Integer.parseInt(idGeneroStr);
                String idStreamingStr =request.getParameter("streaming");
                int idStreaming =Integer.parseInt(idStreamingStr);


                if(idGenero==0&&idStreaming==0){
                    response.sendRedirect("listaPeliculas");
                }
                else{

                        ArrayList<pelicula> listaPeliculas = peliculaDao.listarPeliculasFiltradas(idGenero,idStreaming);
                        request.setAttribute("listaPeliculas", listaPeliculas);
                        RequestDispatcher view = request.getRequestDispatcher("listaPeliculas.jsp");
                        view.forward(request,response);

                }


                request.getParameter("streaming");
                viewFiltro.forward(request,response);
                break;

            case "editar":


                int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                String titulo = request.getParameter("titulo");
                String director = request.getParameter("director");
                int anoPublicacion = Integer.parseInt(request.getParameter("anoPublicacion"));
                double rating = Double.parseDouble(request.getParameter("rating"));
                double boxOffice = Double.parseDouble(request.getParameter("boxOffice"));
                String genero = request.getParameter("genero");

                peliculaDao.editarPelicula(idPelicula, titulo,director,anoPublicacion,rating,boxOffice);
                response.sendRedirect(request.getContextPath()+"/listaPeliculas?action=listar");
                break;


        }
    }




}
