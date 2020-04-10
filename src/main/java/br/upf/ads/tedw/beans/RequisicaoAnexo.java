package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: RequisicaoAnexo
 *
 */

@Entity
public class RequisicaoAnexo implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ReqAnexoId")
	@SequenceGenerator(name = "ReqAnexoId", sequenceName = "ReqAnexoId", allocationSize = 1)
	private Long id;
	
	@NotBlank(message = "Informe a descrição do arquivo")
	@Length(min = 10, max = 250, message = "A descrição deve ter entre {min} e {max} caracteres")
	private String descricao;
	
	@NotBlank(message = "Informe o arquivo")
	@Length(min = 5, max = 100, message = "O arquivo deve ter entre {min} e {max}")
	private String arquivo;
	
	@ManyToOne (optional = false)
	private Requisicao requisicao;
	
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
	
	public Requisicao getRequisicao() {
		return requisicao;
	}
	
	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
   
}
