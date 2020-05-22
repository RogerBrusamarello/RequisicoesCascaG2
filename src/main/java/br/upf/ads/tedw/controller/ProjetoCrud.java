package br.upf.ads.tedw.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Cliente;
import br.upf.ads.tedw.beans.Projeto;
import br.upf.ads.tedw.beans.Usuario;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.jsf.JSFUtil;

@ManagedBean
@ViewScoped
public class ProjetoCrud implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean editando;
	private List<Projeto> lista;
	private Projeto selecionado;
	private List<Usuario> usuarios;
	private List<Cliente> clientes;

	@ManagedProperty(value = "#{loginController}")
	private LoginController login;

	public ProjetoCrud() {
		editando = false;
	}

	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}

	public List<Projeto> getLista() {
		return lista;
	}

	public void setLista(List<Projeto> lista) {
		this.lista = lista;
	}

	public Projeto getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Projeto selecionado) {
		this.selecionado = selecionado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@SuppressWarnings({ "unchecked" })
	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from Projeto order by nome").getResultList();
		usuarios = em.createQuery("from Usuario order by nome").getResultList();
		clientes = em.createQuery("from Cliente order by nome").getResultList();
		em.close();
	}

	public void incluir() {
		editando = true;
		selecionado = new Projeto();
		if (login.getPessoaLogada() instanceof Usuario) {
			selecionado.setUsuario((Usuario) login.getPessoaLogada());
		}
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
	public List<Usuario> completeUsuario(String query) {
		EntityManager em = JPAUtil.getEntityManager();
		if ((login.getPessoaLogada() instanceof Usuario)) {
			List<Usuario> results = em
					.createQuery("from Usuario where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' "
							+ ((login.getPessoaLogada() instanceof Usuario)
									? " and id = " + login.getPessoaLogada().getId()
									: "")
							+ " order by nome")
					.getResultList();
			em.close();
			return results;
		} else {
			List<Usuario> results = em.createQuery(
					"from Usuario where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
					.getResultList();
			em.close();
			return results;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Cliente> completeCliente(String query) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Cliente> results = em.createQuery(
				"from Cliente where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
				.getResultList();
		em.close();
		return results;
	}

	public void cancelar() {
		editando = false;
		selecionado = null;
	}

	public LoginController getLogin() {
		return login;
	}

	public void setLogin(LoginController login) {
		this.login = login;
	}
}
