package br.com.caelum.notasfiscais.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.notasfiscais.Dao.UsuarioDAO;
import br.com.caelum.notasfiscais.modelo.Usuario;
import br.com.caelum.notasfiscais.modelo.UsuarioLogado;

@SessionScoped
@ManagedBean
public class LoginBean  {
	
	private Usuario usuario = new Usuario();	
	private UsuarioLogado usuarioLogado =  new UsuarioLogado();
	private boolean logado;
	
	public String efetuaLogin(){
		UsuarioDAO dao = new UsuarioDAO();
		
		boolean loginValido = dao.existe(this.usuario);
		System.out.println("O login era valido?" + loginValido);
		
		if(loginValido){
			this.usuarioLogado.setUsuario(this.usuario);
			return "/produto.xhtml?faces-redirect=true";
		}else{
			this.usuario = new Usuario();
			return "login";
		}
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}

	public boolean isNotLogado() {
		return ! this.usuarioLogado.isLogado();
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	public boolean isLogado() {
		return this.usuarioLogado.isLogado();
	}
	public String logout(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request =(HttpServletRequest) ctx.getExternalContext().getRequest();
		request.getSession().invalidate();
		this.usuarioLogado.setUsuario(null);
		return "login";
		
	}

}
