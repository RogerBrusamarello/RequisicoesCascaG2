package br.upf.ads.tedw.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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
	@Lob
	@Column(nullable = false)
	private String descricao;

	@Lob
	private String arquivo; // nome do arquivo
	
	@Column(length = 100)
	private String arquivoTipo; // tipo PDF, Doc ...
	
	@Lob
	private byte[] bytes;
	
	
	
	
	@ManyToOne(optional = false)
	@NotNull(message = "Selecione o projeto")
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

	@Override
	public String toString() {
		return descricao;
	}

	public String getArquivoTipo() {
		return arquivoTipo;
	}

	public void setArquivoTipo(String arquivoTipo) {
		this.arquivoTipo = arquivoTipo;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

}
