package com.bcms.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcms.JpaUtil;
import com.bcms.model.Comentario;
import com.google.gson.Gson;

@WebServlet("/list_comentario_empresa_servlet")
public class ListComentariosEmpresa extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Entrada...
		long categoria = new Long(req.getParameter("idEmpresa"));
		
		// Retorno...
		EntityManager manager = JpaUtil.getGerenciadorEntidades();
		Query query = manager.createQuery("from Comentario where empresa_id=" + categoria);
		List<Comentario> list_comentarios = query.getResultList();
		manager.close();

		List<ComentarioJSON> listComentariosJSON = new ArrayList<>();
		ComentarioJSON comentarioJSON;
		for (Comentario comentario : list_comentarios) {
			comentarioJSON = new ComentarioJSON();
			comentarioJSON.nome = comentario.getNome();
			comentarioJSON.nota = comentario.getNota();
			comentarioJSON.comentario = comentario.getComentario();
			
			listComentariosJSON.add(comentarioJSON);
		}
		Gson gson = new Gson();
		String JsonString = gson.toJson(listComentariosJSON);

		PrintStream printStream = new PrintStream(resp.getOutputStream());
		printStream.println(JsonString);
		printStream.close();
	}
	
	private class ComentarioJSON {
		private String nome;
		private byte nota;
		private String comentario;
	}
}
