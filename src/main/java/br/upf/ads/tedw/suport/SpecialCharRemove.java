package br.upf.ads.tedw.suport;

public class SpecialCharRemove {

	/**
	 * 
	 * @brief Expressão regular que remove caracteres de uma String
	 * 
	 * @param str  = valor a ser verificado e alterado
	 * 
	 * @param mode = 1: que não são de (a-z A-Z 0-9 )(AUTOMATICAMENTE)
	 * @param mode = 2: que não são de (a-z A-Z ) (AUTOMATICAMENTE)
	 * @param mode = 3: que não são de (0-9) (AUTOMATICAMENTE)
	 * @param mode = 4: para caracteres específicos (MANUAL)
	 * 
	 * @author Eduardo Debastiani Mior
	 * @author Nardeli Miguel Stalter
	 * @Date 16/04/2020
	 * 
	 * @return
	 */
	public static String specialCharRemoveFromString(String str, Integer mode) {

		switch (mode) {
		case 1:
			String regexAll = "[^a-zA-Z0-9 ]";
			str = str.replaceAll(regexAll, "");
			break;

		case 2:
			String regexL = "[^a-zA-Z ]";
			str = str.replaceAll(regexL, "");
			break;

		case 3:
			String regexN = "[^0-9]";
			str = str.replaceAll(regexN, "");
			break;

		case 4:
			String[] charToRemove = { "\\", "-", "/", "_", "(", ")" };
			for (String caracter : charToRemove) {
				str = str.replace(caracter, "");
			}
			break;

		}
		return str;
	}

	/**
	 * 
	 * 
	 * @brief Remove caracteres (charsRemove) da string de entrada.
	 * 
	 * @param str         - string de entrada
	 * @param charsRemove - string aonde deve ser informado os caracteres a ser
	 *                    removido da string de entrada. necessário definir um
	 *                    delimitador. exemplo: charsRemove="x;y;z" --> delimitador
	 *                    eh ";"
	 * @param delimiter   - string usada para separar os caracteres no split interno
	 *                    do método.
	 * 
	 * @author ricardo spinoza
	 * @date 13/06/2012
	 * 
	 * @author Nardeli Miguel Stalter
	 * @date 16/04/2020
	 * 
	 * @return string sem formatação
	 */
	public static String charRemoveFromString(String str, String charsRemove, String delimiter) {

		if (charsRemove != null && charsRemove.length() > 0 && str != null) {

			String[] remover = charsRemove.split(delimiter);

			for (int i = 0; i < remover.length; i++) {
				// System.out.println("i: " + i + " ["+ remover[i]+"]");
				if (str.indexOf(remover[i]) != -1) {
					str = str.replace(remover[i], "");
				}
			}
		}
		return str;
	}
}
