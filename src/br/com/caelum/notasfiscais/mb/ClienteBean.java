package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.ValidationException;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import br.com.caelum.notasfiscais.Dao.DAO;
import br.com.caelum.notasfiscais.modelo.Cliente;

@RequestScoped
@ManagedBean
public class ClienteBean {
	private Cliente cliente = new Cliente();
	private String mascaraCpfCnpj;
	private List<Cliente> clientes;
	private Long idCliente;
	

	public Cliente getCliente() {
		return cliente;
	}
	
	public String grava(){
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		/*Cliente c = new Cliente();
		c.setNome(cliente.getNome());
		c.setNumeroDocumento(cliente.getNumeroDocumento());
		System.out.println("cliente: " + cliente.getNome());*/
		dao.adiciona(cliente);
		return "produto?faces-redirect=true";
		
	}
	
	public void comecaComMaiuscula(FacesContext fc,
			UIComponent component, Object value)throws ValidationException{
		String valor = value.toString();
		if(!valor.matches("[A-Z].*")){
			throw new ValidatorException(new FacesMessage("Deveria começar com maiúscula"));
			
		}
	}
	public String trocaMascara(String event){
			String tipo = event;
			if(tipo.equals("cnpj")){
				mascaraCpfCnpj =  "99.999.999/9999-99"; 
			}else{
				mascaraCpfCnpj = "999.999.999-99";
		}
			return mascaraCpfCnpj;
		
	}
	public void busca(){
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);
		cliente = dao.buscaPorId(idCliente);
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<Cliente> getClientes(){
		if (clientes == null){
			System.out.println("carregando clientes");
			clientes = new DAO<Cliente>(Cliente.class).listaTodos();
		}
		return clientes;
	}
	
	public String getMascaraCpfCnpj() {
		return mascaraCpfCnpj;
	}
	public void setMascaraCpfCnpj(String mascaraCpfCnpj) {
		this.mascaraCpfCnpj = mascaraCpfCnpj;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}