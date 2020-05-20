package br.upf.ads.tedw.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.upf.ads.tedw.beans.Administrador;
import br.upf.ads.tedw.beans.Cliente;
import br.upf.ads.tedw.beans.Pessoa;
import br.upf.ads.tedw.beans.Usuario;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.suport.Encrypt;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

	public static final long serialVersionUID = 1L;
	public String email;
	public String senha;
	public Integer permissao = null;
	public Integer vlrCliente;
	public Integer vlrUsuario;
	public Integer vlrAdministrador;
	public String tipoUsuario = null;

	/**
	 * Atributo para controle do usuário logado. É inicializado quando informados
	 * email e senha válidos. Setado para null quando o usuário sair do sistema.
	 */
	public Pessoa pessoaLogada = null;

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

	public Pessoa getPessoaLogada() {
		return pessoaLogada;
	}

	public void setPessoaLogada(Pessoa pessoaLogada) {
		this.pessoaLogada = pessoaLogada;
	}

	public Integer getPermissao() {
		return permissao;
	}

	public void setPermissao(Integer permissao) {
		this.permissao = permissao;
	}

	public Integer getVlrCliente() {
		return vlrCliente;
	}

	public void setVlrCliente(Integer vlrCliente) {
		this.vlrCliente = vlrCliente;
	}

	public Integer getVlrUsuario() {
		return vlrUsuario;
	}

	public void setVlrUsuario(Integer vlrUsuario) {
		this.vlrUsuario = vlrUsuario;
	}

	public Integer getVlrAdministrador() {
		return vlrAdministrador;
	}

	public void setVlrAdministrador(Integer vlrAdministrador) {
		this.vlrAdministrador = vlrAdministrador;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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
			pessoaLogada = null;
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail ou senha inválida!", "");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			return "";
		} else {
			pessoaLogada = list.get(0);
			//System.out.println("Id logado: " + pessoaLogada.getId());
			EntityManager em2 = JPAUtil.getEntityManager();

			/**
			 *  verifica se a pessoa é Administrador
			 */
			qry = em2.createQuery("from Administrador where id = :id ");
			qry.setParameter("id", pessoaLogada.getId());
			@SuppressWarnings("unchecked")
			List<Administrador> listAdmin = qry.getResultList();

			/**
			 * verifica se a pessoa é Usuario
			 */
			qry = em2.createQuery("from Usuario where id = :id ");
			qry.setParameter("id", pessoaLogada.getId());
			@SuppressWarnings("unchecked")
			List<Usuario> listUsuario = qry.getResultList();

			/**
			 *  verifica se a pessoa é Cliente
			 */
			qry = em2.createQuery("from Cliente where id = :id ");
			qry.setParameter("id", pessoaLogada.getId());
			@SuppressWarnings("unchecked")
			List<Cliente> listCliente = qry.getResultList();

			/**
			 * Atribuindo valores para hierarquia em ordem crescente referente ao tipo de
			 * usuário. Quanto maior a hierarquia, maior o valor.
			 * 
			 * 1 (Cliente) - 2 (Usuário) - 3 (Administrador)
			 * 
			 */
			setVlrCliente(1);
			setVlrUsuario(2);
			setVlrAdministrador(3);

			//System.out.println("vlrAdministrador: " + vlrAdministrador);
			//System.out.println("vlrUsuario: " + vlrUsuario);
			//System.out.println("vlrCliente: " + vlrCliente);

			/**
			 * Verifica o perfil de usuário da pessoa logada
			 */
			if (listAdmin.size() > 0) {
				setPermissao(vlrAdministrador);
			} else if (listUsuario.size() > 0) {
				setPermissao(vlrUsuario);
			} else if (listCliente.size() > 0) {
				setPermissao(vlrCliente);
			} else {
				permissao = null;
			}

			/**
			 *  Texto do perfil de usuário à exibir junto ao nome da pessoa que está logada
			 */
			switch (permissao) {
			case 3:
				setTipoUsuario("(Administrador)");
				break;
			case 2:
				setTipoUsuario("(Usuário)");
				break;
			case 1:
				setTipoUsuario("(Cliente)");
				break;
			default:
				break;
			}

			//System.out.println("Permissão: " + permissao);
			em2.close();
			return "/faces/Privado/Home.xhtml";
		}
	}

	/**
	 * Método responsável por desconectar o usuário e abrir a página de login
	 * 
	 * @throws IOException
	 * 
	 * @throws Exception
	 */
	public void sair() throws IOException {
		setPessoaLogada(null);
		setPermissao(null);
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário Desconectado!", "");
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
		HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String contextPath = req.getContextPath();
		res.sendRedirect(contextPath + "/faces/index.xhtml");
		FacesContext.getCurrentInstance().responseComplete();
	}
}