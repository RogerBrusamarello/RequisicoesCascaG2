package br.upf.ads.tedw.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Requisicao;
import br.upf.ads.tedw.beans.RequisicaoAnexo;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.jsf.JSFUtil;

@ManagedBean
@ViewScoped
public class RequisicaoAnexoCrud implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean editando;
	private List<RequisicaoAnexo> lista;
	private RequisicaoAnexo selecionado;
	private List<Requisicao> requisicoes;
	
	public RequisicaoAnexoCrud() {
		editando = false;
	}
	
	public Boolean getEditando() {
		return editando;
	}
	
	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public List<RequisicaoAnexo> getLista() {
		return lista;
	}
	
	public void setLista (List<RequisicaoAnexo> lista) {
		this.lista = lista;
	}
	
	public RequisicaoAnexo getSelecionado() {
		return selecionado;
	}
	
	public void setSelecionado (RequisicaoAnexo selecionado) {
		this.selecionado = selecionado;
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
		lista = em.createQuery("from RequisicaoAnexo").getResultList();
		requisicoes = em.createQuery("from Requisicao order by titulo").getResultList();
		em.close();
	}
	
	public void incluir() {
		editando = true;
		selecionado = new RequisicaoAnexo();
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
			JSFUtil.messagemDeErro("Ocorreu um erro ao remover os dados");
		}
	}
	
	public void cancelar() {
		editando = false;
		selecionado = null;
	}
}
