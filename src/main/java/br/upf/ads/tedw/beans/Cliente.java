package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity

public class Cliente extends Pessoa implements Serializable {

	@NotBlank(message = "Informe a função que o cliente executa.")
	@Length(min = 2, max = 30, message = "A função precisa ter entre {min} e {max} caracteres.")
	private String funcao;

	public Cliente() {

	}

	public String getFuncao() {
		return this.funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}
