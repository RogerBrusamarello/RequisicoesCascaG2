package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.upf.ads.tedw.annotation.EnumValidate;
import br.upf.ads.tedw.suport.Letra;

/**
 * Entity implementation class for Entity: RequisicaoAndamento
 *
 */
@Entity

public class RequisicaoAndamento implements Serializable {

	@Version
	private Integer version;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequisicaoAndamentoId")
	@SequenceGenerator(name = "RequisicaoAndamentoId", sequenceName = "RequisicaoAndamentoId", allocationSize = 1)
	private Long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date data;

	@NotBlank(message = "Informe o título")
	@Length(min = 10, max = 100, message = "O título precisa ter entre {min} e {max} caracteres")
	@Column(length = 100, nullable = false)
	private String titulo;

	@Lob
	private String descricao;

	@NotNull(message = "Informe a quantidade de horas realizadas no atendimento:")
	@Min(value = 0, message = "Deve ter pelo menos 1 hora realizada")
	private Integer horasRealizadas;

	@NotNull(message = "Informe o status da requisição (F se Finalizada ou N se Não Finalizada)")
	// Definir validação para F ou N
	// Se informou N deve setar a data de finalização na requisição
	// para null, reabrindo a requisição
	// @StringOptionsValid(message = "Status valido", opcoes= {"F", "N"})
	@Column(nullable = false)
	@EnumValidate(enumClass = Letra.class, message = "Você deve informar um status válido.")
	private Character status;

	@ManyToOne(optional = false)
	@NotNull
	private Pessoa pessoa;

	@ManyToOne(optional = false)
	@NotNull
	private Requisicao requisicao;

	private static final long serialVersionUID = 1L;

	public RequisicaoAndamento() {
		super();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
		RequisicaoAndamento other = (RequisicaoAndamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return titulo;
	}
}
