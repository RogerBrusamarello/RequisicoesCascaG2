package br.upf.ads.tedw.relatorios;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
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

	SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

	private static final long serialVersionUID = 1L;
	private Date dataIni;
	private Date dataFim;
	private Projeto projeto;
	private boolean ignoraStatus;
	private String statusProgramada;
	private String statusFinalizada;
	private String searchProgramada;
	private String searchFinalizada;

	@PostConstruct
	public void init() {
		statusProgramada = "Todas";
		statusFinalizada = "Todas";
	}

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

			String dataI = formatador.format(dataIni);
			String dataF = formatador.format(dataFim);

			System.out.println("Programada: " + statusProgramada);

			if (statusProgramada.equals("Todas")) {
				searchProgramada = "";
			} else if (statusProgramada.equals("Sim")) {
				searchProgramada = "requisicaoprogramada.datainicio IS NOT NULL";
			} else if (statusProgramada.equals("Não")) {
				searchProgramada = "requisicaoprogramada.datainicio IS NULL";
			} else {
				searchProgramada = "";
			}

			if (statusFinalizada.equals("Todas")) {
				searchFinalizada = "";
			} else if (statusFinalizada.equals("Sim")) {
				searchFinalizada = "requisicaoandamento.status = 'F'";
			} else if (statusFinalizada.equals("Não")) {
				searchFinalizada = "(requisicaoandamento.status = 'N' OR requisicaoandamento.requisicao_id IS NULL)";
			} else {
				searchFinalizada = "";
			}

			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();

			String sql = "WHERE " + (projeto != null ? "requisicao.projeto_id = " + projeto.getId() + " AND " : "")
					+ searchProgramada
					+ ((!statusProgramada.equals("Todas") && !statusFinalizada.equals("Todas")) ? " AND " : "")
					+ searchFinalizada
					+ ((!statusProgramada.equals("Todas") || !statusFinalizada.equals("Todas")) ? " AND " : "")
					+ "(requisicao.datacriada BETWEEN '" + dataI + "' AND '" + dataF + "') "

					+ "ORDER BY requisicao.id";

			parameters.put("filtroRequisicao", sql);

			System.out.println(sql);

			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Requisicao/RequisicaoProgramadaRelGroup.jasper",
					parameters);

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

	public boolean isIgnoraStatus() {
		return ignoraStatus;
	}

	public void setIgnoraStatus(boolean ignoraStatus) {
		this.ignoraStatus = ignoraStatus;
	}

	public String getstatusProgramada() {
		return statusProgramada;
	}

	public void setStatusProgramada(String statusProgramada) {
		this.statusProgramada = statusProgramada;
	}

	public String getstatusFinalizada() {
		return statusFinalizada;
	}

	public void setStatusFinalizada(String statusFinalizada) {
		this.statusFinalizada = statusFinalizada;
	}

}
