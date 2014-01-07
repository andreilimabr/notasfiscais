package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.Dao.DAO;
import br.com.caelum.notasfiscais.modelo.Cliente;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

@ManagedBean
@ViewScoped
public class NotaFiscalBean implements Serializable{
	
	private NotaFiscal nota = new NotaFiscal();
	private Cliente cliente = new Cliente();
	private Item item = new Item();
	private Long idProduto;

	
	
	

	//metodo para buscar usuario do banco atraves do id
	public void selectCliente(AjaxBehaviorEvent event){
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		cliente = dao.buscaPorId(cliente.getId());
		nota.setCnpj(cliente.getNumeroDocumento());
		nota.setNome(cliente.getNome());
	}

	public String adiciona(){
		DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
		dao.adiciona(nota);
		this.nota = new NotaFiscal();
		return "nota-fical";
		
	}
	public void guardaItem(){
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		Produto produto = dao.buscaPorId(this.idProduto);
		this.item.setProduto(produto);
		this.item.setValorUnitario(produto.getPreco());
		this.nota.getItens().add(this.item);
		this.item.setNotaFiscal(this.nota);
		
	}
	
	
	
	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	public Item getItem() {
		return item;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public NotaFiscal getNota() {
		return nota;
	}	
}
