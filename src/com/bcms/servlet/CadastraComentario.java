package com.bcms.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcms.JpaUtil;
import com.bcms.model.Comentario;
import com.bcms.model.Empresa;
import com.google.gson.Gson;

@WebServlet("/cadastrar_comentario")
public class CadastraComentario extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Entrada...
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String stringJson = br.readLine();
		br.close();
		
		Gson gson = new Gson();
		ComentarioJSON comentarioJSON = gson.fromJson(stringJson, ComentarioJSON.class);
		
		EntityManager manager = JpaUtil.getGerenciadorEntidades();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Empresa empresa = manager.find(Empresa.class, comentarioJSON.idEmpresa);
		
		Comentario comentario = new Comentario();
		comentario.setNome(comentarioJSON.nome);
		comentario.setComentario(comentarioJSON.comentario);
		comentario.setNota((byte)comentarioJSON.estrelas);
		comentario.setEmpresa(empresa);
		
		manager.persist(comentario);
		tx.commit();
		manager.close();
	}
	
	private class ComentarioJSON {
        private long idEmpresa;
        private String nome;
        private String email;
        private int estrelas;
        private String comentario;
    }
}
