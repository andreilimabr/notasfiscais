package br.com.caelum.notasfiscais.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.caelum.notasfiscais.Dao.DAO;
import br.com.caelum.notasfiscais.modelo.Usuario;


@SessionScoped
@ManagedBean
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private Long usuarioId;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String grava(){
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		dao.adiciona(usuario);
		this.usuario = new Usuario();
		
		return "produto?faces-redirect=true";
		
		
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
}
