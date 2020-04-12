package br.upf.ads.tedw.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Requisicao;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.jsf.JSFUtil;

@ManagedBean
@ViewScoped
public class RequisicaoCrud implements Serializable {
	
	private Boolean editando;
	private List<Requisicao> lista;
	private Requisicao selecionado;
	
	public RequisicaoCrud() {
		editando = false;
	}
	
	public Boolean getEditando() {
		return editando;	
	}
	
	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public List<Requisicao> getLista(){
		return lista;
	}
	
	public void setLista (List<Requisicao> lista) {
		this.lista = lista;
	}
	
	public Requisicao getSelecionado() {
		return selecionado;
	}
	
	public void setSelecionado (Requisicao selecionado) {
		this.selecionado = selecionado;
	}
	
	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from Requisicao").getResultList();
		em.close();
	}
	
	public void incluir() {
		editando = true;
		selecionado = new Requisicao();
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
	
	public void cancelar() {
		editando = false;
		selecionado = null;
	}
}
