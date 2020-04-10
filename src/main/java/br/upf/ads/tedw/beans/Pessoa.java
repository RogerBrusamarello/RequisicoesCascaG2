package br.upf.ads.tedw.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Email;
import javax.persistence.Column;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Pessoa
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PessoaId")
	@SequenceGenerator(name = "PessoaID", sequenceName = "PessoaId", allocationSize = 1)
	private Long id;

	@NotBlank(message = "Informe o nome da pessoa.")
	@Length(min = 2, max = 70, message = "O nome da pessoa deve ter entre {min} e {max} caracteres.")
	private String nome;

	@NotBlank(message = "Informe o CPF.")
	@Length(min = 2, max = 14, message = "O número precisa ser de um CPF válido e deve ter no máximo {max} caracteres.")
	@CPF
	@Column(nullable = false, unique = true)
	private String cpf;

	@NotBlank(message = "Informe o RG.")
	@Length(min = 2, max = 20, message = "O número do RG precisa ser válido.")
	private String rg;

	@NotBlank(message = "Informe o E-mail.")
	@Length(min = 10, max = 80, message = "O e-mail precisa ter formato válido")
	@Email
	private String email;

	@NotBlank(message = "Informe o número do telefone celular.")
	@Length(min = 11, max = 11, message = "O número do celular deve ter {max} caracteres")
	private String celular;

	@NotBlank(message = "Outras informações.")
	@Length(min = 0, max = 255)
	private String outrasInformacoes;

	public Pessoa() {

	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getOutrasInformacoes() {
		return this.outrasInformacoes;
	}

	public void setOutrasInformacoes(String outrasInformacoes) {
		this.outrasInformacoes = outrasInformacoes;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
