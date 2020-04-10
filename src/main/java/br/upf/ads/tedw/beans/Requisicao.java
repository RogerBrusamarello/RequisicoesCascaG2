package br.upf.ads.tedw.beans;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Requisicao
 *
 */
@Entity

public class Requisicao implements Serializable {

	   
	@Id
	private Long id;
	private String titulo;
	private String descricao;
	private Date dataCriada;
	private Date prazoLimite;
	private Integer prioridade;
	private Integer horasPrevistas;
	private Integer horasRealizadas;
	private Date dataFinalizada;
	private Projeto projeto;
	private static final long serialVersionUID = 1L;

	public Requisicao() {
		super();
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
		return this.projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
   
}
