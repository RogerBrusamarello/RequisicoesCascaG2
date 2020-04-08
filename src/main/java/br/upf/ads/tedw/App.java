package br.upf.ads.tedw;

import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("tedw");
	}
}
