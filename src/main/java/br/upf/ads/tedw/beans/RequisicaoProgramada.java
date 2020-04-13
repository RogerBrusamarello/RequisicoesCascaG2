package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

/**
 * Entity implementation class for Entity: RequisicaoProgramada
 *
 */
@Entity

public class RequisicaoProgramada implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequisicaoProgramadaId")
	@SequenceGenerator(name = "RequisicaoProgramadaId", sequenceName = "RequisicaoProgramadaId", allocationSize = 1)
	private long id;

	private Date data;

	@NotBlank(message = "Informe a data de inicio")
	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@NotBlank(message = "Informe a data de termino")
	@Temporal(TemporalType.DATE)
	private Date dataTermino;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotBlank(message = "Selecione uma requisição")
	private Requisicao requisicao;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotBlank(message = "Selecione uma pessoa")
	private Pessoa pessoa;

	private static final long serialVersionUID = 1L;

	public RequisicaoProgramada() {
		super();
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

}
