package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import br.com.caelum.notasfiscais.Dao.DAO;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

//@ViewBean
public class NotaFiscalBean_bkp implements Serializable {
	private NotaFiscal nota = new NotaFiscal();
	private Item item = new Item();
	private Long idProduto;
	
	
	public void gravar() {
		DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
		dao.adiciona(this.nota);
		this.nota = new NotaFiscal();
	}
	
	public void guardaItem() {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		Produto produto = dao.buscaPorId(this.idProduto);
		this.item.setProduto(produto);
		this.item.setValorUnitario(produto.getPreco());
		this.nota.getItens().add(this.item);
		this.item.setNotaFiscal(this.nota);
		
		this.item = new Item();
	}
	
	public void removeItem(Item item) {
		this.nota.getItens().remove(item);
	}
	
	public NotaFiscal getNota() {
		return this.nota;
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
	
}
