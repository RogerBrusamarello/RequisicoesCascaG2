package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
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

	@Version
	private Integer version;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RequisicaoId")
	@SequenceGenerator(name = "RequisicaoId", sequenceName = "RequisicaoId", allocationSize = 1)
	private Long id;

	@NotBlank(message = "Informe o título")
	@Length(min = 2, max = 100, message = "O titulo deve ter entre {min} e {max} caracteres")
	@Column(length = 100, nullable = false)
	private String titulo;

	@NotBlank(message = "Informe a descrição")
	@Lob
	private String descricao;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "Informe a data de criação")
	private Date dataCriada;

	@Temporal(TemporalType.DATE)
	private Date prazoLimite;

	@Min(value = 0, message = "A prioridade deve ser igual ou superior a {value}")
	@Max(value = 10, message = "A prioridade deve ser igual ou inferior a {value}")
	@NotNull(message = "Informe a prioridade") // Se usar valor default, este pode ser tirado
	// @Column(precision = 0, columnDefinition = "NOT NULL DEFAULT 0")
	@Column(nullable = false)
	private Integer prioridade;

	@Min(value = 0, message = "As horas previstas devem ser iguais ou superiores a {value}")
	@NotNull(message = "Informe o número de horas previstas")
	@Column(nullable = false)
	private Integer horasPrevistas;

	@NotNull(message = "Informe as horas que foram realizadas")
	// Não deve ser atualizado na base junto com os demais dados do objeto.
	// Deve ser atualizado na base por implementação de regra de negócio sempre que
	// houver registro de horas realizadas em requisição finalizada.
	@Column(updatable = false)
	private Integer horasRealizadas;

	// Não deve ser atualizado com os outros dados do objeto
	// Será atualizada via implementação de regra de negócio a ser implementada para
	// quando registrar um andamento com status de finalização da requisição
	@Column(updatable = false)
	private Date dataFinalizada;

	@ManyToOne(optional = false)
	@NotNull(message = "Selecione o projeto")
	private Projeto projeto;

	@ManyToOne(optional = false)
	@NotNull(message = "Selecione a pessoa solicitante")
	private Pessoa solicitou;

	@ManyToOne(optional = false)
	@NotNull(message = "Selecione a pessoa responsável pela criação desta requisição")
	private Pessoa criou;

	// VER
	// @OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL)
	// private List<RequisicaoAnexo> anexo;

	private static final long serialVersionUID = 1L;

	public Requisicao() {
		super();
		dataCriada = new Date();
		horasRealizadas = 0;
		dataFinalizada = null;
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

	public Pessoa getSolicitou() {
		return solicitou;
	}

	public void setSolicitou(Pessoa solicitou) {
		this.solicitou = solicitou;
	}

	public Pessoa getCriou() {
		return criou;
	}

	public void setCriou(Pessoa criou) {
		this.criou = criou;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	@Override
	public String toString() {
		return titulo;
	}
	
}
