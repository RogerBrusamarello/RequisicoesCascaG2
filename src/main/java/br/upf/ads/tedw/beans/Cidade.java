package br.upf.ads.tedw.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Cidade
 *
 */

@Entity
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CidadeId")
	@SequenceGenerator(name = "CidadeId", sequenceName = "CidadeId", allocationSize = 1)
	private Integer id;

	@NotBlank(message = "Informe o nome da cidade.")
	@Length(min = 2, max = 50, message = "O nome da cidade deve ter entre {min} e {max} caracteres.")
	private String nome;

	@NotBlank(message = "Informe o nome da UF.")
	@Length(min = 2, max = 2, message = "O nome da UF deve ter 2 caracteres.")
	private String uf;

	public Cidade() {

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}