package br.com.caelum.notasfiscais.mb;

import java.awt.Event;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.sun.istack.internal.logging.Logger;

import java.lang.Object;

import br.com.caelum.notasfiscais.Dao.DAO;
import br.com.caelum.notasfiscais.Dao.JPAUtil;
import br.com.caelum.notasfiscais.modelo.Foto;
import br.com.caelum.notasfiscais.modelo.Produto;

import java.io.File;

@RequestScoped
@ManagedBean
public class ProdutoBean implements Serializable {
	private Produto produto = new Produto();
	private List<Produto> produtos;
	private Long produtoId;
	private Produto produtoSelecionado = new Produto();
	
	private List<Foto> fotos = new ArrayList<Foto>();
	private Foto foto = new Foto();
	private StreamedContent imagem = new DefaultStreamedContent();
	
	
	
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
		 this.foto = new Foto();
	 }
	 public void processFileUpload(FileUploadEvent uploadEvent) throws IOException {
		 imagem = new DefaultStreamedContent(uploadEvent.getFile().getInputstream());
		 foto.setProduto(produtoSelecionado);
		 foto.setImagem(uploadEvent.getFile().getContents());
		 
	 }
	 public void criaArquivo(byte[] bs,String arquivo) throws IOException{
		FileOutputStream fos = new FileOutputStream(arquivo);
		fos.write(bs);
		
		fos.flush();
		fos.close();
	 }
	 public void listaFotosProdutos(){
		 
		 DAO<Foto> dao = new DAO<Foto>(Foto.class);
		 dao.buscaPorId(produtoSelecionado.getId());
		 String nomeArquivo= foto.getId() + ".jnpg";
		 
	 }
	 
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	

}
