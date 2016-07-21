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
import com.bcms.model.Empresa;
import com.bcms.model.Imagem;
import com.bcms.model.Noticia;
import com.google.gson.Gson;

@WebServlet("/list_noticias")
public class ListNoticias extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Retorno...
		EntityManager manager = JpaUtil.getGerenciadorEntidades();
		Query query = manager.createQuery("from Noticia");
		List<Noticia> list_noticias = query.getResultList();
		manager.close();

		List<NoticiaJSON> listNoticiaJSON = new ArrayList<>();
		NoticiaJSON noticiaJSON;
		for (Noticia noti : list_noticias) {
			noticiaJSON = new NoticiaJSON();
			noticiaJSON.id = noti.getId();
			noticiaJSON.oquee = noti.getOquee();
			noticiaJSON.quando = noti.getQuando();
			noticiaJSON.onde = noti.getOnde();
			noticiaJSON.horario = noti.getHorario();
			noticiaJSON.quanto = noti.getQuanto();
		
			listNoticiaJSON.add(noticiaJSON);
		}
		Gson gson = new Gson();
		String JsonString = gson.toJson(listNoticiaJSON);

		PrintStream printStream = new PrintStream(resp.getOutputStream());
		printStream.println(JsonString);
		printStream.close();
	}

	private class NoticiaJSON {
		private long id;
		private String oquee;
		private String quando;
		private String onde;
		private String horario;
		private String quanto;
	}
}
