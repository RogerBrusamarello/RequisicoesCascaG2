package br.upf.ads.tedw.relatorios;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Cliente;
import br.upf.ads.tedw.controller.LoginController;
import br.upf.ads.tedw.jpa.JPAUtil;

@ManagedBean
@RequestScoped
public class RequisicaoClienteRelFiltro implements Serializable {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;

	@ManagedProperty(value = "#{loginController}")
	private LoginController login;

	public RequisicaoClienteRelFiltro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Cliente> completeCliente(String query) {
		EntityManager em = JPAUtil.getEntityManager();
		if (login.pessoaLogada instanceof Cliente) {
			@SuppressWarnings("unchecked")
			List<Cliente> results = em.createQuery("FROM Cliente WHERE UPPER(nome) like " + "'"
					+ query.trim().toUpperCase() + "%' " + " and id = " + login.pessoaLogada.getId() + " order by nome")
					.getResultList();
			em.close();
			return results;
		} else {
			@SuppressWarnings("unchecked")
			List<Cliente> results = em.createQuery(
					"from Cliente where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
					.getResultList();
			em.close();
			return results;
		}

	}

	@SuppressWarnings("unchecked")
	public void gerar() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();

			String sql = ((cliente == null)
					? ((login.pessoaLogada instanceof Cliente)
							? "WHERE projeto.cliente_id = " + login.pessoaLogada.getId()
							: "")
					: "WHERE projeto.cliente_id = " + cliente.getId()) + " ORDER BY pessoa.nome, projeto.nome";

			parameters.put("filtroRequisicao", sql);

			System.out.println(sql);

			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Requisicao/RequisicaoClienteRelGroup.jasper",
					parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public LoginController getLogin() {
		return login;
	}

	public void setLogin(LoginController login) {
		this.login = login;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
