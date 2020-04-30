package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: RequisicaoProgramada
 *
 */
@Entity

public class RequisicaoProgramada implements Serializable {

	@Version
	private Integer version;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequisicaoProgramadaId")
	@SequenceGenerator(name = "RequisicaoProgramadaId", sequenceName = "RequisicaoProgramadaId", allocationSize = 1)
	private long id;

	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(nullable = false)
	private Date data;

	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	private Date dataTermino;

	@ManyToOne(optional = false)
	@NotNull
	private Requisicao requisicao;

	@ManyToOne(optional = false)
	@NotNull
	private Pessoa pessoa;

	private static final long serialVersionUID = 1L;

	public RequisicaoProgramada() {
		super();
		data = new Date();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return this.dataTermino;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		RequisicaoProgramada other = (RequisicaoProgramada) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " - " + requisicao + " - " + pessoa;
	}

}
