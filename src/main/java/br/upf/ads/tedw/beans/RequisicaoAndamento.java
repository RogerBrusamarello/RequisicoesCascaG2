package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: RequisicaoAndamento
 *
 */
@Entity

public class RequisicaoAndamento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequisicaoAndamentoId")
	@SequenceGenerator(name = "RequisicaoAndamentoId", sequenceName = "RequisicaoAndamentoId", allocationSize = 1)
	private Long id;

	@NotBlank(message = "Informe a data")
	@Temporal(TemporalType.DATE)
	private Date data;

	@NotBlank(message = "Informe o título")
	@Length(min = 10, max = 100, message = "A prioridade deve ser entre {min} e {max}")
	private String titulo;

	@Length(min = 0, max = 255, message = "A prioridade deve ser entre {min} e {max}")
	private String descricao;

	@Min(value = 1, message = "Deve ter pelo menos 1 hora realizada")
	@NotNull(message = "Você deve informar o número de horas realizadas")
	@Column(nullable = false)
	private Integer horasRealizadas;

	@NotNull(message = "Você deve informar o status da requisição")
	@Column(nullable = false)
	private Character status;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotBlank(message = "Selecione uma pessoa")
	private Pessoa pessoa;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotBlank(message = "Selecione uma requisição")
	private Requisicao requisicao;

	private static final long serialVersionUID = 1L;

	public RequisicaoAndamento() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getHorasRealizadas() {
		return this.horasRealizadas;
	}

	public void setHorasRealizadas(Integer horasRealizadas) {
		this.horasRealizadas = horasRealizadas;
	}

	public Character getStatus() {
		return this.status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Requisicao getRequisicao() {
		return this.requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

}
