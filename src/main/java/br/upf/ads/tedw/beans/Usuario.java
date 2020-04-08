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
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsuarioId")
	@SequenceGenerator(name = "UsuarioID", sequenceName = "UsuarioId", allocationSize = 1)
	private Integer id;

	@NotBlank(message = "Informe o nome do usuário.")
	@Length(min = 3, max = 100, message = "O nome do usuário deve ter entre {min} e {max} caracteres.")
	private String nome;

	@NotBlank(message = "Informe o login do usuário.")
	@Length(min = 2, max = 255, message = "O login do usuário deve ter entre {min} e {max} caracteres.")
	private String login;

	@NotBlank(message = "Informe o email do usuário.")
	@Length(min = 10, max = 255, message = "O email do usuário deve ter entre {min} e {max} caracteres.")
	private String email;

	@NotBlank(message = "Informe a senha do usuário.")
	@Length(min = 6, max = 64, message = "A senha do usuário deve ter entre {min} e {max} caracteres.")
	private String senha;

	public Usuario() {
		super();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return this.nome;
	}

}