package br.upf.ads.tedw.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Pessoa;
import br.upf.ads.tedw.beans.Requisicao;
import br.upf.ads.tedw.beans.RequisicaoProgramada;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.jsf.JSFUtil;

@ManagedBean
@ViewScoped
public class RequisicaoProgramadaCrud implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean editando;
	private List<RequisicaoProgramada> lista;
	private RequisicaoProgramada selecionado;
	private List<Pessoa> pessoas;
	private List<Requisicao> requisicoes;

	public RequisicaoProgramadaCrud() {
		editando = false;
	}

	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}

	public List<RequisicaoProgramada> getLista() {
		return lista;
	}

	public void setLista(List<RequisicaoProgramada> lista) {
		this.lista = lista;
	}

	public RequisicaoProgramada getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(RequisicaoProgramada selecionado) {
		this.selecionado = selecionado;
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

	@SuppressWarnings("unchecked")
	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from RequisicaoProgramada").getResultList();
		requisicoes = em.createQuery("from Requisicao order by titulo").getResultList();
		pessoas = em.createQuery("from Pessoa order by nome").getResultList();
		em.close();
	}

	public void incluir() {
		editando = true;
		selecionado = new RequisicaoProgramada();
	}

	public void alterar() {
		editando = true;
	}

	public void salvar() {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(selecionado);
			em.getTransaction().commit();
			em.close();
			carregarLista();
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.messagemDeErro("Ocorreu um erro ao salvar os dados.");
		}
	}

	public void excluir() {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(selecionado));
			em.getTransaction().commit();
			em.close();
			carregarLista();
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.messagemDeErro("Ocorreu um erro ao remover os dados.");
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
