package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: PessoaRecupercao
 *
 */
@Entity
public class PessoaRecuperacao implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PessoaRecId")
	@SequenceGenerator(name = "PessoaRecId", sequenceName = "PessoaRecId", allocationSize = 1)
	private Long id;
	
	@NotBlank(message = "Informe um E-mail válido.")
	@Length(min = 5, max = 100, message = "O e-mail precisa ter formato válido")
	@Email(message = "Formato de e-mail inválido!")
	@Column(length = 100, nullable = false, unique = true)
	private String email;
	
	@NotBlank(message = "Informe um código válido.")
	@Length(min = 6, max = 6, message = "O código deve possuir {min} caracteres")
	@Column(length = 100, nullable = false, unique = true)
	private String codigo;
	
	private static final long serialVersionUID = 1L;

	public PessoaRecuperacao() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		PessoaRecuperacao other = (PessoaRecuperacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
   
}
