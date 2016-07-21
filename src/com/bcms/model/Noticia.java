package com.bcms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Noticia {
	private long id;
	private String oquee;
	private String quando;
	private String onde;
	private String horario;
	private String quanto;
	//Noticia -0..1-----0..1- Noticia: Para navegabilidade
	private Imagem imagem;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Para usar o auto-incremento do MySql, para esta coluna.
	public long getId() { return id; }
	public String getOquee() { return oquee; }
	public String getQuando() { return quando; }
	public String getOnde() { return onde; }
	public String getHorario() { return horario; }
	public String getQuanto() { return quanto; }
	@OneToOne(mappedBy="noticia")
	public Imagem getImagem() { return imagem; }
	
	public void setId(long id) { this.id = id; }
	public void setOquee(String oquee) { this.oquee = oquee; }
	public void setQuando(String quando) { this.quando = quando; }
	public void setOnde(String onde) { this.onde = onde; }
	public void setHorario(String horario) { this.horario = horario; }
	public void setQuanto(String quanto) { this.quanto = quanto; }
	public void setImagem(Imagem imagem) { this.imagem = imagem; }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((onde == null) ? 0 : onde.hashCode());
		result = prime * result + ((oquee == null) ? 0 : oquee.hashCode());
		result = prime * result + ((quando == null) ? 0 : quando.hashCode());
		result = prime * result + ((quanto == null) ? 0 : quanto.hashCode());
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
		Noticia other = (Noticia) obj;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (id != other.id)
			return false;
		if (onde == null) {
			if (other.onde != null)
				return false;
		} else if (!onde.equals(other.onde))
			return false;
		if (oquee == null) {
			if (other.oquee != null)
				return false;
		} else if (!oquee.equals(other.oquee))
			return false;
		if (quando == null) {
			if (other.quando != null)
				return false;
		} else if (!quando.equals(other.quando))
			return false;
		if (quanto == null) {
			if (other.quanto != null)
				return false;
		} else if (!quanto.equals(other.quanto))
			return false;
		return true;
	}
	
	
}
