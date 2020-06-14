package br.upf.ads.tedw.relatorios;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Cliente;
import br.upf.ads.tedw.beans.Projeto;
import br.upf.ads.tedw.controller.LoginController;
import br.upf.ads.tedw.jpa.JPAUtil;

@ManagedBean
@RequestScoped
public class RequisicaoProgramadaRelFiltro implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date dataIni;
	private Date dataFim;
	private Projeto projeto;
	private boolean statusProgramada;
	private boolean statusFinalizada;

	@ManagedProperty(value = "#{loginController}")
	private LoginController login;

	public RequisicaoProgramadaRelFiltro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Projeto> completeProjeto(String query) {
		EntityManager em = JPAUtil.getEntityManager();
		if (login.pessoaLogada instanceof Cliente) {
			@SuppressWarnings("unchecked")
			List<Projeto> results = em
					.createQuery("from Projeto where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' "
							+ " and cliente.id = " + login.pessoaLogada.getId() + " order by nome")
					.getResultList();
			em.close();
			return results;
		} else {
			@SuppressWarnings("unchecked")
			List<Projeto> results = em.createQuery(
					"from Projeto where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
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

			String sql = "WHERE " + (projeto != null ? "requisicao.projeto_id = " + projeto.getId() + " AND " : "")
					+ (statusProgramada == true ? " " : "")
					+ (statusFinalizada == true ? "requisicaoandamento.status = 'F' AND "
							: "requisicaoandamento.status = 'N' AND ")
					+ "requisicao.datacriada BETWEEN " + dataIni + " AND " + dataFim + " " + "ORDER BY requisicao.id";

			parameters.put("filtroAndRequisicao", sql);

			System.out.println(sql);

			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Requisicao/RequisicaoProgramadaRelGroup.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public Date getDataIni() {
		return dataIni;
	}

	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public LoginController getLogin() {
		return login;
	}

	public void setLogin(LoginController login) {
		this.login = login;
	}

	public boolean isStatusProgramada() {
		return statusProgramada;
	}

	public void setStatusProgramada(boolean statusProgramada) {
		this.statusProgramada = statusProgramada;
	}

	public boolean isStatusFinalizada() {
		return statusFinalizada;
	}

	public void setStatusFinalizada(boolean statusFinalizada) {
		this.statusFinalizada = statusFinalizada;
	}

}
