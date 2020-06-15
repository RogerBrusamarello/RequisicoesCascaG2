package br.upf.ads.tedw.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.file.UploadedFile;

import br.upf.ads.tedw.beans.Cliente;
import br.upf.ads.tedw.beans.Pessoa;
import br.upf.ads.tedw.beans.Projeto;
import br.upf.ads.tedw.beans.Requisicao;
import br.upf.ads.tedw.beans.RequisicaoAnexo;
import br.upf.ads.tedw.jpa.JPAUtil;
import br.upf.ads.tedw.jsf.JSFUtil;

@ManagedBean
@ViewScoped
public class RequisicaoCrud implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean editando;
	private List<Requisicao> lista;
	private Requisicao selecionado;

	private UploadedFile file;
	private RequisicaoAnexo anexoSelecionado;

	@ManagedProperty(value = "#{loginController}")
	private LoginController login;

	public RequisicaoCrud() {
		editando = false;
	}

	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}

	public List<Requisicao> getLista() {
		return lista;
	}

	public void setLista(List<Requisicao> lista) {
		this.lista = lista;
	}

	public Requisicao getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Requisicao selecionado) {
		this.selecionado = selecionado;
	}

	@SuppressWarnings("unchecked")
	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		Query qry;
		if (login.pessoaLogada instanceof Cliente) {
			qry = em.createQuery("from Requisicao where projeto.cliente.id = :id order by titulo");
			qry.setParameter("id", login.pessoaLogada.getId());
			lista = qry.getResultList();

		} else {
			lista = em.createQuery("from Requisicao order by titulo").getResultList();
		}
		em.close();
	}

	@SuppressWarnings("unchecked")
	public void carregarListaX() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from Requisicao order by titulo").getResultList();
		em.close();
	}

	public void incluir() {
		editando = true;
		selecionado = new Requisicao();
		selecionado.setCriou(login.pessoaLogada);
		anexoSelecionado = new RequisicaoAnexo();
	}

	public void alterar() {
		editando = true;
	}

	public void salvar() {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(selecionado);
			em.getTransaction().commit();
			em.close();
			carregarLista();
			JSFUtil.mensagemDeSucessoSalvar();
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErroSalvar();
		}
	}

	public void excluir() {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(selecionado));
			em.getTransaction().commit();
			em.close();
			carregarLista();
			JSFUtil.mensagemDeSucessoExcluir();
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.mensagemDeErroExcluir();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> completePessoa(String query) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Pessoa> results = em.createQuery(
				"from Pessoa where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
				.getResultList();
		em.close();
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Projeto> completeProjeto(String query) {
		EntityManager em = JPAUtil.getEntityManager();
		if (login.pessoaLogada instanceof Cliente) {
			List<Projeto> results = em
					.createQuery("from Projeto where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' "
							+ " and cliente.id = " + login.pessoaLogada.getId() + " order by nome")
					.getResultList();
			em.close();
			return results;
		} else {
			List<Projeto> results = em.createQuery(
					"from Projeto where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
					.getResultList();
			em.close();
			return results;
		}

	}

	public void cancelar() {
		editando = false;
		selecionado = null;
	}

	/**
	 * Controle dos arquivos anexados
	 * 
	 */
	public void incluirAnexo() {
		anexoSelecionado = new RequisicaoAnexo();
	}

	public void salvarAnexo() {
		if (file != null) {
			anexoSelecionado.setRequisicao(selecionado);
			anexoSelecionado.setArquivo(file.getFileName());
			anexoSelecionado.setArquivoTipo(file.getContentType());
			anexoSelecionado.setBytes(file.getContent());
			if (selecionado.getAnexos() == null) {
				selecionado.setAnexos(new ArrayList<RequisicaoAnexo>());
			}
			selecionado.getAnexos().add(anexoSelecionado);
			anexoSelecionado = new RequisicaoAnexo();
		}
	}

	public void downloadAnexo(Integer linha) throws IOException {
		RequisicaoAnexo anexo = selecionado.getAnexos().get(linha);
		byte[] b = anexo.getBytes();
		HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		res.setContentType(anexo.getArquivoTipo());
		res.setHeader("Content-disposition", "inline;filename=" + anexo.getArquivo()); // abre no navegador
		// res.setHeader("Content-disposition",
		// "attachment;filename="+anexo.getArquivo());
		res.getOutputStream().write(b);
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void excluirAnexo(Integer linha) {
		anexoSelecionado = selecionado.getAnexos().get(linha);
		selecionado.getAnexos().remove(anexoSelecionado);
	}

	public void cancelarAnexo() {

	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public RequisicaoAnexo getAnexoSelecionado() {
		return anexoSelecionado;
	}

	public void setAnexoSelecionado(RequisicaoAnexo anexoSelecionado) {
		this.anexoSelecionado = anexoSelecionado;
	}

	public LoginController getLogin() {
		return login;
	}

	public void setLogin(LoginController login) {
		this.login = login;
	}
}
