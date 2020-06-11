package br.upf.ads.tedw.relatorios;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Projeto;
import br.upf.ads.tedw.beans.Requisicao;
import br.upf.ads.tedw.jpa.JPAUtil;

@ManagedBean
@RequestScoped
public class RequisicaoAndamentoRelFiltro implements Serializable {

	private static final long serialVersionUID = 1L;
	private Requisicao requisicao;

	public RequisicaoAndamentoRelFiltro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Projeto> completeProjeto(String query) {
		EntityManager em = JPAUtil.getEntityManager();

		@SuppressWarnings("unchecked")
		List<Projeto> results = em.createQuery(
				"from Projeto where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
				.getResultList();
		em.close();
		return results;

	}

	@SuppressWarnings("unchecked")
	public void gerar() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			// passar os parâmetros
			parameters.put("requisicaoId", requisicao.getId());

			String sql = "and requisicao.id > 0 order by requisicao.id";
			parameters.put("filtroId", sql);

			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Professor/Requisicao/Requisicao2.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

}
