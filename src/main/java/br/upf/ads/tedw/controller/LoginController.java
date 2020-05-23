package br.upf.ads.tedw.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

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
import br.upf.ads.tedw.beans.PessoaRecuperacao;
import br.upf.ads.tedw.beans.Usuario;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.jsf.JSFUtil;
import br.upf.ads.tedw.suport.Email;
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
	public String codigo;

	/**
	 * Atributo para controle do usuário logado. É inicializado quando informados
	 * email e senha válidos. Recebe valor null quando o usuário sair do sistema.
	 */
	public Pessoa pessoaLogada = null;

	/**
	 * Atributo para a pessoa logada que for atualizar alterar a senha
	 */
	public Pessoa pessoaAlterarSenha;

	/**
	 * Senha
	 */
	public String senhaAtual;
	public String novaSenha;
	public Boolean verifica = false;

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

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public Pessoa getPessoaAlterarSenha() {
		return pessoaAlterarSenha;
	}

	public void setPessoaAlterarSenha(Pessoa pessoaAlterarSenha) {
		this.pessoaAlterarSenha = pessoaAlterarSenha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getVerifica() {
		return verifica;
	}

	public void setVerifica(Boolean verifica) {
		this.verifica = verifica;
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
			JSFUtil.mensagemDeErroLogin();
			return "";
		} else {
			pessoaLogada = list.get(0);
			EntityManager em2 = JPAUtil.getEntityManager();
			
			PessoaRecuperacao pr = findPessoaRecuperacaoByEmail(email);
			em2.getTransaction().begin();
			em2.remove(em2.merge(pr));
			em2.getTransaction().commit();

			/**
			 * verifica se a pessoa é Administrador
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
			 * verifica se a pessoa é Cliente
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

			/**
			 * Verifica o perfil de usuário da pessoa logada e atribui correspondente ao
			 * perfil de usuário e será exibido junto ao nome da pessoa que está logada
			 */
			if (listAdmin.size() > 0) {
				setPermissao(vlrAdministrador);
				setTipoUsuario("(ADMINISTRADOR)");
			} else if (listUsuario.size() > 0) {
				setPermissao(vlrUsuario);
				setTipoUsuario("(USUÁRIO)");
			} else if (listCliente.size() > 0) {
				setPermissao(vlrCliente);
				setTipoUsuario("(CLIENTE)");
			} else {
				permissao = null;
			}

			JSFUtil.mensagemDeSucessoLogin();
			em2.close();
			HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			String contextPath = req.getContextPath();
			try {
				res.sendRedirect(contextPath + "/faces/Privado/Home.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().responseComplete();
			return contextPath;
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
		JSFUtil.mensagemDeSucesso("Usuário Desconectado!");
		HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String contextPath = req.getContextPath();
		res.sendRedirect(contextPath + "/faces/index.xhtml");
		FacesContext.getCurrentInstance().responseComplete();
	}

	/**
	 * Alterar senha do usuário logado
	 * 
	 */
	public void alterarSenha() {
		EntityManager em = JPAUtil.getEntityManager();
		Query qry = em.createQuery("from Pessoa where id = :id");
		qry.setParameter("id", pessoaLogada.getId());
		pessoaAlterarSenha = (Pessoa) qry.getResultList().get(0);
		if (Encrypt.encryptMd5(senhaAtual).equals(pessoaAlterarSenha.getSenha())) {
			try {
				pessoaAlterarSenha.setSenha(novaSenha);
				em.getTransaction().begin();
				em.merge(pessoaAlterarSenha);
				em.getTransaction().commit();
				JSFUtil.mensagemDeSucesso("Senha alterada com sucesso!");
			} catch (Throwable e) {
				e.printStackTrace();
				JSFUtil.mensagemDeErroSalvar();
			}
		} else {
			JSFUtil.mensagemDeErro("A senha atual não confere!");
		}
		em.close();
	}

	public PessoaRecuperacao findPessoaRecuperacaoByEmail(String value) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			System.out.println(value);
			Query q = em.createQuery("FROM PessoaRecuperacao o WHERE o.email = '" + value + "'");
			return (PessoaRecuperacao) q.getSingleResult();
		} catch (Throwable e) {
			return null;
		} finally {
			em.close();
		}
	}

	public String recuperarSenha() {
		try {
			String codigoRecuperacao = String.format("%06d", (new Random().nextInt(999999)));
			EntityManager em = JPAUtil.getEntityManager();
			Query qry = em.createQuery("from Pessoa where email = :email");
			qry.setParameter("email", email);
			Pessoa pessoa = (Pessoa) qry.getResultList().get(0);
			PessoaRecuperacao pRec = new PessoaRecuperacao();
			pRec.setEmail(email);
			PessoaRecuperacao antigo = findPessoaRecuperacaoByEmail(email);
			if (antigo != null) {
				pRec = antigo;
			}
			pRec.setCodigo(codigoRecuperacao);
			em.getTransaction().begin();
			em.merge(pRec);
			Email.send(email, "🔐 Recuperação de Senha", "Sistema Gestor de Requisições\nUsuário: " + pessoa.getNome()
					+ "\nSeu código é " + codigoRecuperacao);
			em.getTransaction().commit();
			em.close();
			JSFUtil.mensagemDeSucesso("Código gerado com sucesso! Verifique seu e-mail");
			return "ConfirmarCodigo.xhtml";
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErroSalvar();
		}
		return "";
	}

	public String verificarCodigo() {
		try {
			PessoaRecuperacao p = findPessoaRecuperacaoByEmail(email);
			if (p.getCodigo().equals(codigo)) {
				JSFUtil.mensagemDeSucesso("Código verificado! Insira sua nova senha");
				verifica = true;
				return "DefinirNovaSenha.xhtml";
			} else {
				JSFUtil.mensagemDeErro("Código incorreto!");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErroSalvar();
		}
		return "";
	}

	public Pessoa findPessoaByEmail(String value) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			System.out.println(value);
			Query q = em.createQuery("FROM Pessoa o WHERE o.email = '" + value + "'");
			return (Pessoa) q.getSingleResult();
		} catch (Throwable e) {
			return null;
		} finally {
			em.close();
		}
	}

	public String definirNovaSenha() throws IOException {
		if (verifica) {
			EntityManager em = JPAUtil.getEntityManager();
			Pessoa p = findPessoaByEmail(email);
			PessoaRecuperacao pr = findPessoaRecuperacaoByEmail(email);
			System.out.println("código: " + codigo);
			System.out.println("email: " + email);
			if (pr.getCodigo().equals(codigo)) {
				p.setSenha(novaSenha);
				em.getTransaction().begin();
				em.merge(p);
				em.remove(em.merge(pr));
				em.getTransaction().commit();
				em.close();
				JSFUtil.mensagemDeSucesso("Senha alterada com sucesso!");
			} else {
				JSFUtil.mensagemDeErro("Erro ao alterar a senha. Código inválido!");
			}
		} else {
			JSFUtil.mensagemDeErro("Acesso negado. Gere novo código de recuperação!");
		}
		verifica = false;
		return "LoginForm.xhtml";
	}
}
