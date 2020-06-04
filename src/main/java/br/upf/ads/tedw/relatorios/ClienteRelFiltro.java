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
public class ClienteRelFiltro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nome;

	@ManagedProperty(value = "#{loginController}")
	private LoginController login;

	public ClienteRelFiltro() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public void gerar() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			
			// passar os parâmetros
			String sql = "pessoa.nome = '" + nome + "'";
			//String sql = "pessoa.nome LIKE '%" + nome + "%'";
			parameters.put("filtroClienteFuncao", sql);

			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Pessoa/ClienteFuncaoRel.jasper", parameters);
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
