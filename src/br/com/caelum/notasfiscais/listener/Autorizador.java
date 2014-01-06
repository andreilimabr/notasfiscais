package br.com.caelum.notasfiscais.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.notasfiscais.mb.LoginBean;

public class Autorizador implements PhaseListener   {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		
		//validando, se a requisição veio apartir da tela de login
		if("/login.xhtml".equals(context.getViewRoot().getViewId())){
			return;
		}
		
		//obtendo o loginBean da sessão
		LoginBean loginBean = context.getApplication().
				evaluateExpressionGet(context, "#{loginBean}", LoginBean.class);
		
		if (loginBean.isNotLogado()) {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "login?faces-redirect=true");
			
			//efetua a renderização da tela
			context.renderResponse();
		}
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
