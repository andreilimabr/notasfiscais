package br.com.caelum.notasfiscais.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {// para n�o deixar mexer diretamente no objeto, mexer numa copia dele e ent�o persistir no banco, criar o metodo clone public conforme abaixo
	
	@Id
	@GeneratedValue
	@Column(name = "produto_id")
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Object clone()  throws CloneNotSupportedException  {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
