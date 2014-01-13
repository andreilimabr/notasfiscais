package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;

import br.com.caelum.notasfiscais.Dao.DAO;
import br.com.caelum.notasfiscais.modelo.Foto;
import br.com.caelum.notasfiscais.modelo.Produto;

@RequestScoped
@ManagedBean
public class ProdutoBean implements Serializable {
	private Produto produto = new Produto();
	private List<Produto> produtos;
	private Long produtoId;
	private Produto produtoSelecionado = new Produto();
	
	private Foto foto = new Foto();
	private List<Foto> fotos;
	
	
	
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
		 if (produtos == null){// se n�o colocar o if, ele ir� executar n pesquisas no banco, deixando o sistema mais lento
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
	 public void salvaFoto(){
		 DAO<Foto> dao = new DAO<Foto>(Foto.class);
		 dao.adiciona(foto);
		 
		 FacesContext.getCurrentInstance().addMessage(null, 
				 	new FacesMessage(FacesMessage.SEVERITY_INFO,
				 			"Produto adicionado", "Produto adicionado"));
	 }
	 public void processFileUpload(FileUploadEvent  uploadEvent){
		 foto.setProduto(produtoSelecionado);
		 foto.setImagem(uploadEvent.getFile().getContents());
		 
		 
	 }
	
	 
	 
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}
	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public Produto getProduto() {
		return this.produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Foto getFoto() {
		return foto;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	
	

}
