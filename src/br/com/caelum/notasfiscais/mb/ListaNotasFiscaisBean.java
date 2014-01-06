package br.com.caelum.notasfiscais.mb;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;

import br.com.caelum.notasfiscais.Dao.DAO;
import br.com.caelum.notasfiscais.datamodel.DataModelNotasFiscais;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@ManagedBean
public class ListaNotasFiscaisBean {
	private LazyDataModel<NotaFiscal> dataModel;
	

	public ListaNotasFiscaisBean() {
		this.dataModel = new DataModelNotasFiscais();
		DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
		this.dataModel.setRowCount(dao.contaTodos());
	}
	public LazyDataModel<NotaFiscal> getDataModel() {
		return dataModel;
	}
	
	

}
