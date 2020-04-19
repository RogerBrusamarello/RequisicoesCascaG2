package br.upf.ads.tedw.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.upf.ads.tedw.beans.Estado;
import br.upf.ads.tedw.jpa.JPAUtil;

@FacesConverter("EstadoConverter")
public class EstadoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		//System.out.println("Passou pelo método Object getAsObject");
		try {
			//System.out.println("Passou pelo método Object getAsObject (try). Value: " + value.toString());
			return JPAUtil.getEntityManager().find(Estado.class, Integer.valueOf(value));
		} catch (Exception ex) {
			ex.printStackTrace();
			//System.out.println("Passou pelo método Object getAsObject (catch). Valor: " + value + " - Exception: " + ex.getStackTrace());
			throw new ConverterException("Não foi possível converter o valor [" + value + "] no componente [" + component.getId() + "]", ex);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		//System.out.println("Passou pelo método String getAsString. Value: " + value.toString());
		if (value instanceof Estado) {
			//System.out.println("Passou pelo método String getAsString (If)");
			return ((Estado) value).getId().toString();
		}
		
		return value.toString();
	}

}
