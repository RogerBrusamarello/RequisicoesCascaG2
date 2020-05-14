package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.text.ParseException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import br.upf.ads.tedw.suport.Encrypt;
import br.upf.ads.tedw.suport.SpecialCharRemove;
import br.upf.ads.tedw.suport.StringFormat;

/**
 * Entity implementation class for Entity: Pessoa
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Pessoa implements Serializable {

	@Version
	private Integer version;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PessoaId")
	@SequenceGenerator(name = "PessoaId", sequenceName = "PessoaId", allocationSize = 1)
	private Long id;

	@NotBlank(message = "Informe o nome da pessoa.")
	@Length(min = 2, max = 60, message = "O nome da pessoa deve ter entre {min} e {max} caracteres.")
	@Column(length = 60, nullable = false)
	private String nome;

	@NotBlank(message = "Informe um CPF válido")
	@Length(min = 14, max = 14, message = "O CPF precisa deve ter {max} caracteres e esse formato: XXX.XXX.XXX-XX")
	@CPF(message = "CPF inválido. Precisa ter número válido e estar neste formato: XXX.XXX.XXX-XX")
	@Column(length = 14, nullable = false, unique = true) // Exibir erro de duplicidade no navegador
	private String cpf;

	@NotBlank(message = "Informe um RG válido.")
	@Length(min = 5, max = 11, message = "O número do RG precisa ser válido e ter entre {min} e {max} dígitos")
	@Column(length = 11, nullable = false, unique = false)
	private String rg;

	@NotBlank(message = "Informe um E-mail válido.")
	@Length(min = 5, max = 100, message = "O e-mail precisa ter formato válido")
	@Email(message = "Formato de e-mail inválido!")
	@Column(length = 100, nullable = false, unique = true)
	private String email;

	@NotBlank(message = "Informe o número do telefone celular.")
	@Length(min = 11, max = 15, message = "O número do celular deve ter no máximo {max} caracteres, sendo {min} números")
	// Alteração no valor de "min", implica na necessidade de alteração no método de
	// formatação do método setCelular também
	@Column(length = 15)
	private String celular;

	@NotBlank(message = "Informe uma senha.")
	@Length(min = 6, message = "A senha deve conter no mínimo {min} caracteres")
	@Column(nullable = false)
	private String senha;

	@Length(min = 0, message = "Outras informações (Opcional)")
	@Column(columnDefinition = "text")
	private String outrasInformacoes;

	public Pessoa() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = Encrypt.encryptMd5(senha);
	}

	public String getOutrasInformacoes() {
		return outrasInformacoes;
	}

	public void setOutrasInformacoes(String outrasInformacoes) {
		this.outrasInformacoes = outrasInformacoes;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	@Override
	public String toString() {
		return nome;
	}

}
