package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

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
	@Length(min = 5, max = 250, message = "O titulo deve ter entre {min} e {max} caracteres")
	private String titulo;

	@NotBlank(message = "Informe a descrição")
	@Length(min = 20, max = 250, message = "O descrição deve ter entre {min} e {max} caracteres")
	private String descricao;

	@NotBlank(message = "Informe a data de criação")
	@Temporal(TemporalType.DATE)
	private Date dataCriada;

	@NotBlank(message = "Informe o data limite")
	@Temporal(TemporalType.DATE)
	private Date prazoLimite;

	@NotBlank(message = "Informe a prioriedade")
	@Length(min = 0, max = 10, message = "A prioridade deve ser entre {min} e {max}")
	private Integer prioridade;

	@NotBlank(message = "Informe a quantidade de horas previstas")
	@Length(min = 1, message = "As horas devem ser superior a {min}")
	private Integer horasPrevistas;

	@NotBlank(message = "Informe a quantidade de horas realizadas")
	@Length(min = 1, message = "As horas devem ser superior a {min}")
	private Integer horasRealizadas;

	@NotBlank(message = "Informe a data finalizada")
	@Temporal(TemporalType.DATE)
	private Date dataFinalizada;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotBlank(message = "Selecione um projeto")
	private Projeto projeto;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "requisicao")
	@NotBlank(message = "Selecione um anexo")
	private List<RequisicaoAnexo> anexo;

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

	public List<RequisicaoAnexo> getAnexo() {
		return anexo;
	}

	public void setAnexo(List<RequisicaoAnexo> anexo) {
		this.anexo = anexo;
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
