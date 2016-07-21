package com.bcms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Imagem {
	private long id;
	private byte[] imagem;
	//Empresa -0..1-----0..1- Imagem
	private Empresa empresa;
	//Noticia -0..1-----0..1- Imagem
	private Noticia noticia;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Para usar o auto-incremento do MySql, para esta coluna.
	public long getId() { return id; }
	@Lob
	public byte[] getImagem() { return imagem; }
	@OneToOne
	public Empresa getEmpresa() { return empresa; }
	@OneToOne
	public Noticia getNoticia() { return noticia; }
	
	public void setId(long id) { this.id = id; }
	public void setImagem(byte[] imagem) { this.imagem = imagem; }
	public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
	public void setNoticia(Noticia noticia) { this.noticia = noticia; }
}
