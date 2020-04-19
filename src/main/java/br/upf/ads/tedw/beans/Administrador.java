package br.upf.ads.tedw.beans;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Administrador
 *
 */

@Entity

public class Administrador extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}

}
