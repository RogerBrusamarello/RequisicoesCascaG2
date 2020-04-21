package br.upf.ads.tedw.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.upf.ads.tedw.beans.Requisicao;
import br.upf.ads.tedw.jpa.JPAUtil;

@FacesConverter(value = "RequisicaoConverter")
public class RequisicaoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			return JPAUtil.getEntityManager().find(Requisicao.class, Long.valueOf(value));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConverterException("Não foi possível converter o valor [" + value + "] no componente [" + component.getId() + "]", ex);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Requisicao) {
			return ((Requisicao) value).getId().toString();
		}
		return value.toString();
	}

}
