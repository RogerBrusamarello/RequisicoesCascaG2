package br.upf.ads.tedw;

import java.text.ParseException;

import javax.persistence.Persistence;

public class App {

	public static void main(String[] args) throws ParseException, com.sun.el.parser.ParseException {

		Persistence.createEntityManagerFactory("tedw");

		/**
		 * Testes
		 */

		// System.out.println(stringFormat.freeStringFormat(charRemove.specialCharRemoveFromString("123-456x7897n8965",
		// 3),
		// s "(##) #####-####", 11, 0));
	}

}
