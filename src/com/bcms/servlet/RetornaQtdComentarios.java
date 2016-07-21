package com.bcms.servlet;

import java.io.IOException;
import java.io.PrintStream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcms.JpaUtil;

@WebServlet("/get_qtd_comentarios")
public class RetornaQtdComentarios extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Entrada...
		long idEmpresa = new Long(req.getParameter("idEmpresa"));
		EntityManager manager = JpaUtil.getGerenciadorEntidades();
		TypedQuery<Long> query = manager.createQuery("select count(v) from Comentario v where empresa_id="+idEmpresa
				, Long.class);
		Long quantidade = query.getSingleResult();
		manager.close();
		
		//Retorno
		PrintStream printStream = new PrintStream(resp.getOutputStream());
		printStream.println(quantidade);
		printStream.close();
	}
}
