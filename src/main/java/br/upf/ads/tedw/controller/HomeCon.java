package br.upf.ads.tedw.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import br.upf.ads.tedw.relatorios.RelatorioUtil;

@ManagedBean
@SessionScoped
public class HomeCon implements Serializable {

	private static final long serialVersionUID = 1L;

	public void relClientes() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Pessoa/ClienteRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public void relUsuarios() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Pessoa/UsuarioRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public void relAdministradores() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Pessoa/AdministradorRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public void relPessoas() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Pessoa/PessoaRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public void relProjeto() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Projeto/ProjetosRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public void relProjetoUsuario() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Projeto/ProjetoUsuarioRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public void relProjetoCliente() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Projeto/ProjetoClienteRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public void relRequisicao() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Requisicao/RequisicaoRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public void relRequisicaoSolicitou() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Requisicao/RequisicaoSolicitouRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public void relRequisicaoCriou() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Requisicao/RequisicaoCriouRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public void relAndamentoRequisicao() {
		try {
			@SuppressWarnings("rawtypes")
			HashMap parameters = new HashMap();
			RelatorioUtil.rodarRelatorioPDF("WEB-INF/Relatorios/Requisicao/RequisicaoAndamentoRel.jasper", parameters);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(e.getMessage()));
		}
	}

	public HomeCon() {
		super();
	}

	public void abrirPopup(String url, int largura, int altura, boolean modal) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", modal);
		options.put("width", largura);
		options.put("height", altura);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		options.put("resizable", false);
		options.put("minimizable", true);
		options.put("maximizable", true);
		// até Primefaces 6.1
		// RequestContext.getCurrentInstance().openDialog(url, options, null);
		// a partir do Primefaces 6.2 e 7.0
		PrimeFaces.current().dialog().openDynamic(url, options, null);
	}
}
