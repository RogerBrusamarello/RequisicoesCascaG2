package br.upf.ads.tedw.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {

	/**
	 * Mensagens de ERRO
	 * 
	 * @param mensagem
	 */

	// Mensagem de erro (Editável)
	public static void mensagemDeErro(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Mensagem de erro (Login)
	public static void mensagemDeErroLogin() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha ou E-mail não confere!", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Mensagem de erro (Salvar/Editar)
	public static void mensagemDeErroSalvar() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao salvar os dados!", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	// Mensagem de erro (Excluir)
		public static void mensagemDeErroExcluir() {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao remover os dados!", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}


	/**
	 * Mensagens de SUCESSO
	 * 
	 * @param mensagem
	 */

	// Mensagem de sucesso (Editável)
	public static void mensagemDeSucesso(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Mensagem de sucesso (Login)
	public static void mensagemDeSucessoLogin() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login realizado com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Mensagem de sucesso (Salvar/Alterar)
	public static void mensagemDeSucessoSalvar() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	// Mensagem de sucesso (Salvar/Alterar)
	public static void mensagemDeSucessoExcluir() {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados removidos com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
