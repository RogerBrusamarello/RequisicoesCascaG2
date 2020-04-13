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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Requisicao
 *
 */

@Entity
public class Requisicao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequisicaoId")
	@SequenceGenerator(name = "RequisicaoId", sequenceName = "RequisicaoId", allocationSize = 1)
	private Long id;

	@NotBlank(message = "Informe o título")
	@Length(min = 2, max = 100, message = "O titulo deve ter entre {min} e {max} caracteres")
	@NotNull
	@Column(length = 100, nullable = false)
	private String titulo;

	@NotBlank(message = "Informe a descrição")
	private String descricao;

	@NotBlank(message = "Informe a data de criação")
	@Temporal(TemporalType.DATE)
	// Tornar Campo não editável ?
	@NotNull
	private Date dataCriada;

	@NotBlank(message = "Informe o data limite, caso tenha")
	private Date prazoLimite;

	@NotBlank(message = "Informe a prioridade")
	@Length(min = 0, max = 10, message = "A prioridade deve ser entre {min} e {max}")
	@Min(0)
	@Max(10)
	@NotNull // Se usar valor default, este pode ser tirado
	// @Column(precision = 0, columnDefinition = "NOT NULL DEFAULT 0")
	@Column(nullable = false) // Forma acima não aceita. Valor Default = 0 ?
	private Integer prioridade;

	@NotBlank(message = "Informe a quantidade de horas previstas")
	@Length(min = 0, message = "As horas devem ser ser iguais ou superiores a {min}")
	@Min(0)
	@NotNull
	@Column(nullable = false)
	private Integer horasPrevistas;

	@NotBlank(message = "Informe a quantidade de horas realizadas")
	// @Length(min = 1, message = "As horas devem superior a {min}")
	// Deve inicializar com zero quando uma requisição é inserida.
	// Não deve ser atualizado na base junto com os demais dados do objeto.
	// Deve ser atualizado na base por implementação de regra de negócio sempre que
	// houver registro de horas realizadas em requisição finalizada.
	// @Column(columnDefinition = "DEFAULT 0") // <== Não aceita desta forma
	private Integer horasRealizadas;

	@NotBlank(message = "Informe a data finalizada")
	// Quando inserido inicializa com Null
	// Não deve ser atualizado com os outros dados do objeto
	// Será atualizada via implementação de regra de negócio a ser implementada para
	// quando registrar um andamento com status de finalização da requisição
	// @Column(columnDefinition = "DEFAULT NULL")
	private Date dataFinalizada;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@NotBlank(message = "Selecione o projeto")
	@NotNull
	private Projeto projeto;

	private static final long serialVersionUID = 1L;

	public Requisicao() {

	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDataCriada() {
		return this.dataCriada;
	}

	public void setDataCriada(Date dataCriada) {
		this.dataCriada = dataCriada;
	}

	public Date getPrazoLimite() {
		return this.prazoLimite;
	}

	public void setPrazoLimite(Date prazoLimite) {
		this.prazoLimite = prazoLimite;
	}

	public Integer getPrioridade() {
		return this.prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getHorasPrevistas() {
		return this.horasPrevistas;
	}

	public void setHorasPrevistas(Integer horasPrevistas) {
		this.horasPrevistas = horasPrevistas;
	}

	public Integer getHorasRealizadas() {
		return this.horasRealizadas;
	}

	public void setHorasRealizadas(Integer horasRealizadas) {
		this.horasRealizadas = horasRealizadas;
	}

	public Date getDataFinalizada() {
		return this.dataFinalizada;
	}

	public void setDataFinalizada(Date dataFinalizada) {
		this.dataFinalizada = dataFinalizada;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjetos(Projeto projeto) {
		this.projeto = projeto;
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
		Requisicao other = (Requisicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
