package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.jboss.solder.logging.Logger;
import org.primefaces.event.FlowEvent;

import br.com.caelum.notasfiscais.Dao.DAO;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

@ManagedBean
@ViewScoped
public class NotaFiscalBean implements Serializable{
	
	private NotaFiscal nota = new NotaFiscal();
	private Item item = new Item();
	private Long idProduto;
	
	private boolean skip;
	private static Logger logger = Logger.getLogger(NotaFiscal.class.getName()); 
	

	//metodo para buscar usuario do banco atraves do id
//	public void selectCliente(AjaxBehaviorEvent event){
//		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
//		cliente = dao.buscaPorId(cliente.getId());
//		nota.setCnpj(cliente.getNumeroDocumento());
//		nota.setNome(cliente.getNome());
//	}

	public NotaFiscal getNota() {
		return nota;
	}
	public void adiciona(ActionEvent actionEvent){
		DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
		dao.adiciona(nota);
		this.nota = new NotaFiscal();
		
	}
	public void guardaItem(){
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		Produto produto = dao.buscaPorId(this.idProduto);
		this.item.setProduto(produto);
		this.item.setValorUnitario(produto.getPreco());
		this.nota.getItens().add(this.item);
		this.item.setNotaFiscal(this.nota);
		
	}
	 public String onFlowProcess(FlowEvent event) {  
	        logger.info("Current wizard step:" + event.getOldStep());  
	        logger.info("Next step:" + event.getNewStep());  
	          
	        if(skip) {  
	            skip = false;   //reset in case user goes back  
	            return "confirm";  
	        }  
	        else {  
	            return event.getNewStep();  
	        }  
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
	public boolean isSkip() {
		return skip;
	}
	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
}
