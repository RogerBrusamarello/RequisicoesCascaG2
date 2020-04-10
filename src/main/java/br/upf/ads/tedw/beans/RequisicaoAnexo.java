package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: RequisicaoAnexo
 *
 */
@Entity

public class RequisicaoAnexo implements Serializable {

	   
	@Id
	private Long id;
	private String descricao;
	private String arquivo;
	private static final long serialVersionUID = 1L;

	public RequisicaoAnexo() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	public String getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
   
}
