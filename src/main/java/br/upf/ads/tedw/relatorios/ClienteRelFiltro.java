package br.upf.ads.tedw.relatorios;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.upf.ads.tedw.controller.LoginController;

@ManagedBean
@RequestScoped
public class ClienteRelFiltro implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String funcao;
	private boolean value1;
	private boolean value2;
	private boolean value3;
	private String valuePath;
	private String valueFilter1;
	private String valueFilter2;
	private String valueFilter3;

	@ManagedProperty(value = "#{loginController}")
	private LoginController login;

	public ClienteRelFiltro() {
		super();
	}

	@SuppressWarnings({ "unchecked" })
	public void gerar() {
		try {
			if (value1) {
				valuePath = "WEB-INF/Relatorios/Pessoa/ClienteGroupFuncaoRel.jasper";
				valueFilter1 = "cliente.funcao, ";
			} else {
				valuePath = "WEB-INF/Relatorios/Pessoa/ClienteFiltroRel.jasper";
				valueFilter1 = "";
			}

			if (value2) {
				valueFilter2 = "cliente.id";
			} else {
				valueFilter2 = "";
			}

			if (value3) {
				valueFilter3 = " OR UPPER(pessoa.email) LIKE UPPER('%" + nome + "%')";
			} else {
				valueFilter3 = "";
			}

			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();

			System.out.println("nome: " + nome + "_");
			System.out.println("função: " + funcao + "_");
			System.out.println("Valor 1: " + value1);
			System.out.println("Valor 2: " + value2);
			System.out.println("Valor 3: " + value3);
			
			// passar os parâmetros
			String sql = (nome != null || funcao != null ? (nome != null ? "UPPER(pessoa.nome) LIKE UPPER('%" + nome + "%')" : "")
					+ (nome != null && funcao != null ? " AND " : "") 
					+ (funcao != null ? "UPPER(cliente.funcao) LIKE UPPER('%" + funcao + "%')" : "") + valueFilter3
					+ (value1 == true || value2 == true ? " ORDER BY " : "") + valueFilter1 + valueFilter2 : "");
			
			/*String sql = (nome == "" || funcao == "" ? "" : (nome == "" ? "" : "UPPER(pessoa.nome) LIKE UPPER('%" + nome + "%')")
			+ (nome == "" && funcao == "" ? "" : " AND ") 
			+ (funcao == "" ? "" : "UPPER(cliente.funcao) LIKE UPPER('%" + funcao + "%')") + valueFilter3
			+ (value1 == true || value2 == true ? " ORDER BY " : "") + valueFilter1 + valueFilter2);*/

			System.out.println(sql);

			parameters.put("filtroClienteFuncao", sql);

			RelatorioUtil.rodarRelatorioPDF(valuePath, parameters);
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

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public boolean isValue1() {
		return value1;
	}

	public void setValue1(boolean value1) {
		this.value1 = value1;
	}

	public boolean isValue2() {
		return value2;
	}

	public void setValue2(boolean value2) {
		this.value2 = value2;
	}

	public boolean isValue3() {
		return value3;
	}

	public void setValue3(boolean value3) {
		this.value3 = value3;
	}

}
