package br.upf.ads.tedw.suport;

import javax.swing.text.MaskFormatter;

import com.sun.el.parser.ParseException;

public class StringFormat {

	/**
	 * 
	 * @brief Formatar String cfe valor e formato especificado
	 * 
	 * @param value    = Valor a ser formatado
	 * @param pattern  = Formato de saída desejado. Exemplo: "(##) #####-####"
	 * @param maxChar  = Número máximo de caracteres à formatar
	 * @param position = 0: quando número de caracteres for superior ao limite,
	 *                 desconsidera os Últimos
	 * @param position = 1: quando número de caracteres for superior ao limite,
	 *                 desconsidera os Primeiros
	 * 
	 * @throws java.text.ParseException
	 * @throws ParseException
	 * 
	 * @author Nardeli Miguel Stalter
	 * @date 16/04/2020
	 * 
	 * @return
	 * 
	 */

	public static String freeStringFormat(String value, String pattern, Integer maxChar, Integer position)
			throws java.text.ParseException, ParseException {

		if (position == 0) {
			value = value.substring(0, maxChar);
		} else {
			value = value.substring((value.length() - maxChar), value.length());
		}

		MaskFormatter mf;
		mf = new MaskFormatter(pattern);
		mf.setValueContainsLiteralCharacters(false);

		return mf.valueToString(value);

	}

	// Exemplos de saída:
	// String CEP = freeStringFormat("12345678","##.###-###", 5, 0);
	// String Celular = freeStringFormat("1234567890123","(##) #####-####", 11, 0);
}
