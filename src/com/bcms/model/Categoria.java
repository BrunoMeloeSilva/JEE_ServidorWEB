package com.bcms.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	private long id;
	private String nome;
	//Empresa -*-------1- Categoria: Para navegabilidade
	private List<Empresa> listEmpresas;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Para usar o auto-incremento do MySql, para esta coluna.
	public long getId() { return id; }
	public String getNome() { return nome; }
	@OneToMany(mappedBy="categotia")
	public List<Empresa> getListEmpresas() { return listEmpresas; }
	
	public void setId(long id) { this.id = id; }
	public void setNome(String nome) { this.nome = nome; }
	public void setListEmpresas(List<Empresa> listEmpresas) { this.listEmpresas = listEmpresas; }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Categoria other = (Categoria) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
}
