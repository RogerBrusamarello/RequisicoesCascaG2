package br.upf.ads.tedw.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.RequisicaoAndamento;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.jsf.JSFUtil;

@ManagedBean
@ViewScoped
public class RequisicaoAndamentoCrud implements Serializable {

	private Boolean editando;
	private List<RequisicaoAndamento> lista;
	private RequisicaoAndamento selecionado;

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

	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from RequisicaoAndamento").getResultList();
		em.close();
	}

	public void incluir() {
		editando = true;
		selecionado = new RequisicaoAndamento();
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
			em.remove(selecionado);
			em.getTransaction().commit();
			em.close();
			carregarLista();
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.messagemDeErro("Ocorreu um erro ao remover os dados.");
		}
	}

	public void cancelar() {
		editando = false;
		selecionado = null;
	}

}
