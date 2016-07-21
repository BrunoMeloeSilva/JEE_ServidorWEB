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

@WebServlet("/get_imagem_noticia")
public class RetornaImagemNoticia extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Entrada...
		Long noticiaId = new Long(req.getParameter("idNoticia"));
		// Retorno...
		EntityManager manager = JpaUtil.getGerenciadorEntidades();
		Imagem imagem = manager.find(Imagem.class, noticiaId);//Tá errado, deve pegar pela chave estrangeira.
		
		
		BufferedImage img = null;
		if (imagem.getImagem() != null) {
			img = ImageIO.read(new ByteArrayInputStream(imagem.getImagem())); 
			resp.setContentType("image/jpg");
			OutputStream out = resp.getOutputStream();
			ImageIO.write(img, "JPG", out);
		} else {
			System.out.println("Noticia não possui foto.");
		}
		manager.close();
	}
}
