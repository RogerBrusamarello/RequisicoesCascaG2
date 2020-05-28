package br.upf.ads.tedw.jpa;

import java.util.Date;

import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Administrador;
import br.upf.ads.tedw.beans.Cliente;
import br.upf.ads.tedw.beans.Projeto;
import br.upf.ads.tedw.beans.Requisicao;
import br.upf.ads.tedw.beans.Usuario;
import br.upf.ads.tedw.controller.AdministradorCrud;
import br.upf.ads.tedw.controller.ClienteCrud;
import br.upf.ads.tedw.controller.ProjetoCrud;
import br.upf.ads.tedw.controller.UsuarioCrud;
import br.upf.ads.tedw.suport.Email;

public class CriarBase {

	public static void main(String[] args) throws Throwable {

		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();

		/**
		 * Popular banco de dados
		 */

		Usuario u = new Usuario();
		Administrador a = new Administrador();
		Cliente c = new Cliente();
		Projeto proj = new Projeto();
		Requisicao req = new Requisicao();

		ClienteCrud cc = new ClienteCrud();
		UsuarioCrud uc = new UsuarioCrud();
		AdministradorCrud ac = new AdministradorCrud();
		ProjetoCrud pc = new ProjetoCrud();

		/**
		 * Pessoas
		 */
		u.setNome("Pedro Madureira");
		u.setCpf("421.794.710-09");
		u.setEmail("pedro@empresa.com");
		u.setRg("123456789");
		u.setSenha("123456");
		u.setCelular("(54) 91234-1234");
		em.merge(u);
		
		u.setNome("Ana Terra");
		u.setCpf("787.847.350-15");
		u.setEmail("ana@empresa.com");
		u.setRg("9873221564");
		u.setSenha("123456");
		u.setCelular("(54) 98888-1234");
		em.merge(u);

		a.setNome("Carlos Drummond de Andrade");
		a.setCpf("464.515.340-48");
		a.setEmail("admin@admin.com");
		a.setRg("8040506090");
		a.setSenha("123456");
		a.setCelular("(54) 99999-9999");
		em.merge(a);

		c.setNome("Mariana Silva");
		c.setCpf("257.779.390-15");
		c.setFuncao("Investidor(a)");
		c.setEmail("mariana@silva.com.br");
		c.setRg("3020106090");
		c.setSenha("123456");
		c.setCelular("(66) 96666-6666");
		em.merge(c);

		c.setNome("Osvaldo Mendes");
		c.setCpf("511.010.870-64");
		c.setFuncao("Empresário(a)");
		c.setEmail("osvaldo@madeira.com.br");
		c.setRg("7050506090");
		c.setSenha("123456");
		c.setCelular("(11) 98765-4321");
		em.merge(c);
		
		c.setNome("Cleber Pitangueira");
		c.setCpf("110.079.580-49");
		c.setFuncao("Empresário(a)");
		c.setEmail("cleber@pitangueira.com");
		c.setRg("7058236090");
		c.setSenha("123456");
		c.setCelular("(11) 97775-4321");
		em.merge(c);
		
		c.setNome("Antônio Schmidt");
		c.setCpf("974.047.960-08");
		c.setFuncao("Agricultor(a)");
		c.setEmail("antonio@schmidt.com");
		c.setRg("7922506090");
		c.setSenha("123456");
		c.setCelular("(11) 96584-1111");
		em.merge(c);
		
		c.setNome("Amanda Marques");
		c.setCpf("250.792.330-01");
		c.setFuncao("Corretor(a)");
		c.setEmail("amanda@marques.com");
		c.setRg("6521406090");
		c.setSenha("123456");
		c.setCelular("(11) 91254-4421");
		em.merge(c);
		
		c.setNome("Douglas Mello");
		c.setCpf("379.826.380-90");
		c.setFuncao("Investidor(a)");
		c.setEmail("douglas@mello.com");
		c.setRg("9991406090");
		c.setSenha("123456");
		c.setCelular("(11) 98888-4421");
		em.merge(c);
		
		em.getTransaction().commit();
		em.getTransaction().begin();

		/**
		 * Projetos
		 */
		uc.carregarLista();
		u = uc.getLista().get(0);
		cc.carregarLista();
		c = cc.getLista().get(0);

		proj.setNome("Projeto Requisições Master");
		proj.setDescricao("Sem descrição para este projeto");
		proj.setCliente(c);
		proj.setUsuario(u);
		em.merge(proj);

		cc.carregarLista();
		c = cc.getLista().get(1);
		
		proj.setNome("Projeto Requisições Super");
		proj.setDescricao("Sem descrição para este projeto");
		proj.setCliente(c);
		proj.setUsuario(u);
		em.merge(proj);
		
		em.getTransaction().commit();
		em.getTransaction().begin();

		/**
		 * Requisições
		 */
		pc.carregarLista();
		proj = pc.getLista().get(0);
		ac.carregarLista();
		a = ac.getLista().get(0);
		cc.carregarLista();
		c = cc.getLista().get(0);
		
		req.setTitulo("Requisição número 1");
		req.setDescricao("Incluir função X que execute o cálculo...");
		req.setDataCriada(new Date());
		req.setPrioridade(2);
		req.setHorasPrevistas(1);
		req.setProjeto(proj);
		req.setSolicitou(a);
		req.setCriou(c);
		em.merge(req);

		pc.carregarLista();
		proj = pc.getLista().get(0);
		cc.carregarLista();
		c = cc.getLista().get(0);
		
		req.setTitulo("Requisição número 2");
		req.setDescricao("Incluir função YZ para realizar a tarefa de...");
		req.setDataCriada(new Date());
		req.setPrioridade(6);
		req.setHorasPrevistas(3);
		req.setProjeto(proj);
		req.setSolicitou(c);
		req.setCriou(c);
		em.merge(req);

		pc.carregarLista();
		proj = pc.getLista().get(1);
		uc.carregarLista();
		u = uc.getLista().get(0);
		cc.carregarLista();
		c = cc.getLista().get(1);
		
		req.setTitulo("Requisição PRO");
		req.setDescricao("Incluir função PRO2. Mais informações no decorrer");
		req.setDataCriada(new Date());
		req.setPrioridade(2);
		req.setHorasPrevistas(5);
		req.setProjeto(proj);
		req.setSolicitou(c);
		req.setCriou(u);
		em.merge(req);

		/**
		 * RequisicaoAndamento e RequisicaoProgramada não definidos...
		 */

		em.getTransaction().commit();
		em.close();
	}
}