package br.upf.ads.tedw.beans;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Usuario
 *
 */

@Entity

public class Usuario extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}

}