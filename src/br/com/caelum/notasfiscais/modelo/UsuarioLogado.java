package br.com.caelum.notasfiscais.modelo;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named @SessionScoped
public class UsuarioLogado {
	private Usuario usuario;
	
	public boolean isLogado(){
		return usuario != null;
	}

//	public Usuario getUsuario() {
//		return usuario;
//	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
