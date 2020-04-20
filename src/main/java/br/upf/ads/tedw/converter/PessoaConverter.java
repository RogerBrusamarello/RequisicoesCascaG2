package br.upf.ads.tedw.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.upf.ads.tedw.beans.Pessoa;
import br.upf.ads.tedw.jpa.JPAUtil;

@FacesConverter(value = "PessoaConverter")
public class PessoaConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			return JPAUtil.getEntityManager().find(Pessoa.class, Integer.valueOf(value));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConverterException("Não foi possível converter o valor [" + value + "] no componente [" + component.getId() + "]", ex);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Pessoa) {
			return ((Pessoa) value).getId().toString();
		}
		return value.toString();
	}

}
