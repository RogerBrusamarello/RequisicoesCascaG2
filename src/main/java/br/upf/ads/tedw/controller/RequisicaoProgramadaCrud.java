package br.upf.ads.tedw.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.RequisicaoProgramada;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.jsf.JSFUtil;

@ManagedBean
@ViewScoped
public class RequisicaoProgramadaCrud implements Serializable {

	private Boolean editando;
	private List<RequisicaoProgramada> lista;
	private RequisicaoProgramada selecionado;

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

	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from RequisicaoProgramada").getResultList();
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
