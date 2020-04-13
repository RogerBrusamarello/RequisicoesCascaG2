package br.upf.ads.tedw.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@Length(min = 2, max = 100, message = "A descrição deve ter entre {min} e {max} caracteres")
	@NotNull
	@Column(length = 100, nullable = false)
	private String descricao;

	@NotBlank(message = "Selecione o arquivo")
	// Armazenar no banco com um tipo Lob ?
	private String arquivo;

	@NotBlank(message = "Selecione o projeto")
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@NotNull
	private Requisicao requisicao;

	private static final long serialVersionUID = 1L;

	public RequisicaoAnexo() {

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
		RequisicaoAnexo other = (RequisicaoAnexo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
