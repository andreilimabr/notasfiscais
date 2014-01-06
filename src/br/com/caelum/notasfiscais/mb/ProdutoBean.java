package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.caelum.notasfiscais.Dao.DAO;
import br.com.caelum.notasfiscais.modelo.Produto;

@RequestScoped
@ManagedBean
public class ProdutoBean {
	private Produto produto = new Produto();
	private List<Produto> produtos;
	private Long produtoId;
	
	public Produto getProduto() {
		return this.produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	 public String grava(){
		 System.out.println("chamando o grava...");
	     DAO<Produto> dao = new DAO<Produto>(Produto.class);  
	     
	     if(produto.getId() == null){
	       	dao.adiciona(produto);	        	
	     }else{
	       	dao.atualiza(produto);
	      }
	       this.produtos = dao.listaTodos();
	       this.produto = new Produto();
	       
	       return "/produto.xhtml?faces-redirect=true";

	    }
	
	 public List<Produto> getProdutos(){
		 if (produtos == null){// se não colocar o if, ele irá executar n pesquisas no banco, deixando o sistema mais lento
			 System.out.println("Carregando produtos...");
			 produtos = new DAO<Produto>(Produto.class).listaTodos();
		 }
		 return produtos;
	 }
	 public void remove(Produto p){
		 DAO<Produto> dao = new DAO<Produto>(Produto.class);
		 dao.remove(p);
		 this.produtos = dao.listaTodos();
	 }
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	

}
