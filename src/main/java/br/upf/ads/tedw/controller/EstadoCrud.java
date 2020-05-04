package br.upf.ads.tedw.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Estado;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.jsf.JSFUtil;

@ManagedBean
@ViewScoped
public class EstadoCrud implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean editando;
	private List<Estado> lista;
	private Estado selecionado;

	public EstadoCrud() {
		editando = false;
	}

	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}

	public List<Estado> getLista() {
		return lista;
	}

	public void setLista(List<Estado> lista) {
		this.lista = lista;
	}

	public Estado getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Estado selecionado) {
		this.selecionado = selecionado;
	}

	@SuppressWarnings("unchecked")
	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from Estado order by nome").getResultList();
		em.close();
	}

	public void incluir() {
		editando = true;
		selecionado = new Estado();
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

