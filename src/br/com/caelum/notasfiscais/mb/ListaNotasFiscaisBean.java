package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;

import br.com.caelum.notasfiscais.Annotations.ViewBean;
import br.com.caelum.notasfiscais.Dao.DAO;
import br.com.caelum.notasfiscais.datamodel.DataModelNotasFiscais;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@ViewBean
public class ListaNotasFiscaisBean implements Serializable {
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
