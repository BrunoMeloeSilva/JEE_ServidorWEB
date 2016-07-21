package com.bcms.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

import com.bcms.JpaUtil;

@Entity
//@Table(name = "tab_veiculo"): Opcional, caso desejasse mudar o nome da tabela para o banco
public class Empresa {

	private long id;
	private String nomeFantasia;
	private String telefone;
	private String email;
	private String endereco;
	private String horario;
	private int qtdComentarios;
	private String descricao;
	//Empresa -1------0..*- Comentario: Para navegabilidade
	private List<Comentario> listCometarios;
	//Empresa -0..1-----0..1- Imagem: Para navegabilidade
	private Imagem imagem;
	//Empresa -*-------1- Categoria
	private Categoria categotia;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Para usar o auto-incremento do MySql, para esta coluna.
	public long getId() { return id; }
	//Esta annotation é opcional, para melhor especificar o tamanho do varvhar(X) no banco e nao permitir nulos,
	//poderia inclusive ter mais parametros, como o [name = "ano_fabricacao"], para modificar o nome da coluna na tabela.
	@Column(length = 50, nullable = false)
	public String getNomeFantasia() { return nomeFantasia; }
	public String getTelefone() { return telefone; }
	public String getEmail() { return email; }
	public String getEndereco() { return endereco; }
	public String getHorario() { return horario; }
	//A ser preenchido pelo Metodos de callback: calcularQtdComentarios.
	@Transient
	public int getQtdComentarios() { return qtdComentarios; }
	public String getDescricao() { return descricao; }
	@OneToMany(mappedBy="empresa")
	public List<Comentario> getListCometarios() { return listCometarios; }
	@OneToOne(mappedBy = "empresa")
	public Imagem getImagem() { return imagem; }
	@ManyToOne
	public Categoria getCategotia() { return categotia; }
	
	public void setId(long id) { this.id = id; }
	public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }
	public void setTelefone(String telefone) { this.telefone = telefone; }
	public void setEmail(String email) { this.email = email; }
	public void setEndereco(String endereco) { this.endereco = endereco; }
	public void setHorario(String horario) { this.horario = horario; }
	public void setQtdComentarios(int qtdComentarios) { this.qtdComentarios = qtdComentarios; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	public void setListCometarios(List<Comentario> listCometarios) { this.listCometarios = listCometarios; }
	public void setImagem(Imagem imagem) { this.imagem = imagem; }
	public void setCategotia(Categoria categotia) { this.categotia = categotia; }
	
	//Metodos de callback:
	@PostLoad
	@PostPersist
	@PostUpdate
	public void calcularQtdComentarios() {
		EntityManager manager = JpaUtil.getGerenciadorEntidades();
		TypedQuery<Long> query = manager.createQuery("select count(v) from Comentario v where empresa_id="+this.getId()
				, Long.class);
		Long quantidade = query.getSingleResult();
		manager.close();
		
		setQtdComentarios(quantidade.intValue());
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result + qtdComentarios;
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
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
		Empresa other = (Empresa) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (id != other.id)
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (qtdComentarios != other.qtdComentarios)
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nomeFantasia=" + nomeFantasia + ", telefone=" + telefone + ", email=" + email
				+ ", endereco=" + endereco + ", horario=" + horario + ", qtdComentarios=" + qtdComentarios
				+ ", descricao=" + descricao + "]";
	}
}
