package com.bcms.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcms.JpaUtil;
import com.bcms.model.Empresa;
import com.google.gson.Gson;

@WebServlet("/list_empresa_servlet")
public class ListEmpresasCategoria extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Entrada...
		long categoria = new Long(req.getParameter("categoria"));
		
		//Retorno...
		EntityManager manager = JpaUtil.getGerenciadorEntidades();
		TypedQuery<Empresa> query = manager.createQuery("from Empresa where categotia_id="+categoria, Empresa.class);
		List<Empresa> list_empresas = query.getResultList();
		manager.close();
		
		List<EmpresaJSON> listEmpresasJSON = new ArrayList<>();
		EmpresaJSON empresaJSON;
		for (Empresa emp : list_empresas) {
			empresaJSON = new EmpresaJSON();
			empresaJSON.id = emp.getId();
			empresaJSON.nomeFantasia = emp.getNomeFantasia();
			empresaJSON.telefone = emp.getTelefone();
			empresaJSON.email = emp.getEmail();
			empresaJSON.endereco = emp.getEndereco();
			System.out.println(empresaJSON.endereco);
			empresaJSON.horario = emp.getHorario();
			empresaJSON.qtdComentarios = emp.getQtdComentarios();
			empresaJSON.descricao = emp.getDescricao();
			listEmpresasJSON.add(empresaJSON);
		}
		Gson gson = new Gson();
		String JsonString = gson.toJson(listEmpresasJSON);
		System.out.println(JsonString);
		//retorna o enconding do eclipse
		//System.out.println(System.getProperty("file.encoding"));		
		resp.setContentType("text/html;charset=UTF-8");
		PrintStream printStream = new PrintStream(resp.getOutputStream());
		printStream.println(JsonString);
		printStream.close();
	}
	
	private class EmpresaJSON {
		private long id;
		private String nomeFantasia;
		private String telefone;
		private String email;
		private String endereco;
		private String horario;
		private int qtdComentarios;
		private String descricao;
	}
}
/* Tive problemas com Enconding, pra ajustar:
 * 1. Ajustei o encoding do workspace do eclipse para utf-8
 * 2. Para visualizar correto no browser setei resp.setContentType("text/html;charset=UTF-8");
 */
