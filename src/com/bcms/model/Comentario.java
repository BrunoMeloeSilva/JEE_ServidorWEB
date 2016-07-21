/*
 * Empresa -1------*- Comentario
 */
package com.bcms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comentario {
	private long id;
	private String nome;
	private byte nota;
	private String comentario;
	private Empresa empresa;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Para usar o auto-incremento do MySql, para esta coluna.
	public long getId() { return id; }
	@Column(length=50)
	public String getNome() { return nome; }
	public byte getNota() { return nota; }
	@Lob
	public String getComentario() { return comentario; }
	@ManyToOne(optional=false)
	public Empresa getEmpresa() { return empresa; }
	
	public void setId(long id) { this.id = id; }
	public void setNome(String nome) { this.nome = nome; }
	public void setNota(byte nota) { this.nota = nota; }
	public void setComentario(String comentario) { this.comentario = comentario; }
	public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + nota;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nota != other.nota)
			return false;
		return true;
	}
	
	
	
}