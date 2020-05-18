package br.upf.ads.tedw.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
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
import br.upf.ads.tedw.suport.Encrypt;

@ManagedBean
@ViewScoped
public class RequisicaoCrud implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean editando;
	private List<Requisicao> lista;
	private Requisicao selecionado;
	private List<Projeto> projetos;
	private List<Pessoa> pessoas;

	// Controle dos arquivos anexados
	private UploadedFile file;
	private RequisicaoAnexo anexoSelecionado;

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

	public void cancelarAnexo() {

	}

	public void excluirAnexo(Long id) {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(anexoSelecionado));
			em.getTransaction().commit();
			em.close();
			//carregarLista(id);
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.messagemDeErro("Ocorreu um erro ao remover os dados.");
		}
	}
	
	public void downloadAnexo(Integer linha) throws IOException {
		RequisicaoAnexo anexo = selecionado.getAnexos().get(linha); 
        byte[] b = anexo.getBytes();
        HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse(); 
        res.setContentType(anexo.getArquivoTipo());
        res.setHeader("Content-disposition", "inline;filename="+anexo.getArquivo()); // abre no navegador
        //res.setHeader("Content-disposition", "attachment;filename="+anexo.getArquivo());
        res.getOutputStream().write(b);
        FacesContext.getCurrentInstance().responseComplete();
	}
	
	
	
	

	// ------------------------------------------

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

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@SuppressWarnings("unchecked")
	public void carregarLista(Long id) {
		
		System.out.println("Entrou no carregarLista(). id: " + id);
		
		EntityManager em = JPAUtil.getEntityManager();
		Query qry = em.createQuery("from Cliente where id = :id");
		qry.setParameter("id", id);
		pessoas = qry.getResultList();
		
		if (pessoas.size() > 0) {
			
			qry = em.createQuery("from Projeto where cliente_id = :id");
			qry.setParameter("id", id);
			List<Projeto> listProjetos = qry.getResultList();
			
			if (listProjetos.size() > 0) {
				qry = em.createQuery("from Requisicao where projeto_id = :id order by titulo");
				qry.setParameter("id", listProjetos.get(0).getId());
				lista = qry.getResultList();
				
				qry = em.createQuery("from Projeto where id = :id order by nome");
				qry.setParameter("id", listProjetos.get(0).getId());
				projetos = qry.getResultList();
				
				qry = em.createQuery("from Pessoa where id = :id order by nome");
				qry.setParameter("id", id);
				pessoas = qry.getResultList();
				
			} else {
				lista = null;
				projetos = null;
				pessoas = null;
			}
			
		} else {
			lista = em.createQuery("from Requisicao order by titulo").getResultList();
			projetos = em.createQuery("from Projeto order by nome").getResultList();
			pessoas = em.createQuery("from Pessoa order by nome").getResultList();
		}
		em.close();
	}

	public void incluir() {
		editando = true;
		selecionado = new Requisicao();
		anexoSelecionado = new RequisicaoAnexo();
	}

	public void alterar() {
		editando = true;
	}

	public void salvar(Long id) {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(selecionado);
			em.getTransaction().commit();
			em.close();
			carregarLista(id);
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.messagemDeErro("Ocorreu um erro ao salvar os dados.");
		}
	}

	public void excluir(Long id) {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(selecionado));
			em.getTransaction().commit();
			em.close();
			carregarLista(id);
		} catch (Throwable e) {
			e.printStackTrace();
			JSFUtil.messagemDeErro("Ocorreu um erro ao remover os dados.");
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
		List<Projeto> results = em.createQuery(
				"from Projeto where upper(nome) like " + "'" + query.trim().toUpperCase() + "%' " + "order by nome")
				.getResultList();
		em.close();
		return results;
	}

	public void cancelar() {
		editando = false;
		selecionado = null;
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
}
