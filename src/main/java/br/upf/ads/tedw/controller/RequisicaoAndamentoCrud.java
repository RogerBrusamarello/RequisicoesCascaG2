package br.upf.ads.tedw.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Pessoa;
import br.upf.ads.tedw.beans.Requisicao;
import br.upf.ads.tedw.beans.RequisicaoAndamento;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.jsf.JSFUtil;

@ManagedBean
@ViewScoped
public class RequisicaoAndamentoCrud implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean editando;
	private List<RequisicaoAndamento> lista;
	private RequisicaoAndamento selecionado;
	private List<Pessoa> pessoas;
	private List<Requisicao> requisicoes;

	private Integer horasAnt;

	public RequisicaoAndamentoCrud() {
		editando = false;
	}

	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}

	public List<RequisicaoAndamento> getLista() {
		return lista;
	}

	public void setLista(List<RequisicaoAndamento> lista) {
		this.lista = lista;
	}

	public RequisicaoAndamento getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(RequisicaoAndamento selecionado) {
		this.selecionado = selecionado;
	}

	@SuppressWarnings("unchecked")
	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from RequisicaoAndamento").getResultList();
		requisicoes = em.createQuery("from Requisicao order by titulo").getResultList();
		pessoas = em.createQuery("from Pessoa order by nome").getResultList();
		em.close();
	}

	public void incluir() {
		editando = true;
		selecionado = new RequisicaoAndamento();
		horasAnt = 0;
	}

	public void alterar() {
		editando = true;
		horasAnt = selecionado.getHorasRealizadas();
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}

	public void salvar() {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();

			selecionado.getRequisicao().setHorasRealizadas(
					selecionado.getRequisicao().getHorasRealizadas() - horasAnt + selecionado.getHorasRealizadas());

			if (selecionado.getStatus().equals('F')) {
				selecionado.getRequisicao().setDataFinalizada(selecionado.getData());
			} else {
				selecionado.getRequisicao().setDataFinalizada(null);
			}

			em.merge(selecionado);
			em.merge(selecionado.getRequisicao());
			em.getTransaction().commit();
			em.close();
			carregarLista();
			JSFUtil.mensagemDeSucessoSalvar();
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErroSalvar();
		}
	}

	public void excluir() {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			selecionado.getRequisicao().setHorasRealizadas(
					selecionado.getRequisicao().getHorasRealizadas() - selecionado.getHorasRealizadas());
			em.remove(em.merge(selecionado));
			em.getTransaction().commit();
			em.close();
			carregarLista();
			JSFUtil.mensagemDeSucessoExcluir();
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErroExcluir();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> completePessoa(String query) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Pessoa> results = em.createQuery(
				"from Pessoa where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
				.getResultList();
		em.close();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Requisicao> completeRequisicao(String query) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Requisicao> results = em.createQuery("from Requisicao where upper(titulo) like " + "'"
				+ query.trim().toUpperCase() + "%' " + "order by titulo").getResultList();
		em.close();
		return results;
	}

	public void cancelar() {
		editando = false;
		selecionado = null;
	}
}
