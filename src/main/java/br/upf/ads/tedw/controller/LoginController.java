package br.upf.ads.tedw.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.upf.ads.tedw.beans.Pessoa;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.suport.Encrypt;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

	public static final long serialVersionUID = 1L;
	public String email;
	public String senha;

	/**
	 * Atributo para controle do usuário logado. É inicializado quando informados
	 * email e senha válidos. Setado para null quando o usuário sair do sistema.
	 */
	public Pessoa usuarioLogado = null;

	public LoginController() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Pessoa usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * Método responsável por validar o email e senha do usuário. Se for válido
	 * inicializa o usuário logado com a instancia do usuário respectivo ao email e
	 * senha informados e redireciona para a tela principal da aplicação.
	 * 
	 * @throws Exception
	 */
	public String entrar() {
		EntityManager em = JPAUtil.getEntityManager();
		Query qry = em.createQuery("from Pessoa where email = :email and senha = :senha");
		qry.setParameter("email", email);
		qry.setParameter("senha", Encrypt.encryptMd5(senha));
		@SuppressWarnings("unchecked")
		List<Pessoa> list = qry.getResultList();
		em.close();
		if (list.size() <= 0) {
			usuarioLogado = null;
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail ou senha inválida!", "");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			return "";
		} else {
			usuarioLogado = list.get(0);
			return "/faces/Privado/Home.xhtml";
		}
	}

	/**
	 * Método responsável por desconectar o usuário e abrir a página de login
	 * 
	 * @throws Exception
	 */
	public String sair() {
		setUsuarioLogado(null);
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário Desconectado!", "");
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
		// return "../Login/LoginForm.xhtml";
		// return "/faces/index.xhtml";
		return "index.xhtml";
	}
}