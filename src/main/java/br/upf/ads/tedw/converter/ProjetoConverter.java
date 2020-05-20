package br.upf.ads.tedw.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.upf.ads.tedw.beans.Projeto;
import br.upf.ads.tedw.jpa.JPAUtil;

@FacesConverter(value = "ProjetoConverter")
public class ProjetoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			return JPAUtil.getEntityManager().find(Projeto.class, Long.valueOf(value));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConverterException(
					"Não foi possível converter o valor [" + value + "] no componente [" + component.getId() + "]", ex);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Projeto) {
			return ((Projeto) value).getId().toString();
		}
		return value.toString();
	}
}
