package com.bcms.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcms.JpaUtil;
import com.bcms.model.Imagem;

@WebServlet("/get_imagem")
public class RetornaImagem extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Entrada...
		String idEmpresa = null;
		idEmpresa = req.getParameter("idEmpresa");
		// Retorno...
		EntityManager manager = JpaUtil.getGerenciadorEntidades();
		Imagem imagem = null;
		if(idEmpresa != null) {
			Long empresaId = new Long(idEmpresa);
			imagem = manager.find(Imagem.class, empresaId);
		}
		
		BufferedImage img = null;
		if (imagem.getImagem() != null) {
			img = ImageIO.read(new ByteArrayInputStream(imagem.getImagem())); 
			resp.setContentType("image/jpg");
			OutputStream out = resp.getOutputStream();
			ImageIO.write(img, "JPG", out);
		} else {
			System.out.println("Empresa não possui foto.");
		}
		manager.close();
	}
}
