package br.upf.ads.tedw.jpa;

import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Administrador;
import br.upf.ads.tedw.beans.Cliente;
import br.upf.ads.tedw.beans.Usuario;
import br.upf.ads.tedw.suport.Encrypt;

public class CriarBase {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManager();
		
        em.getTransaction().begin();
        
        // Popular pessoas
        Usuario u = new Usuario();
        u.setNome("Pedro Secretário");
        u.setCpf("421.794.710-09");
        u.setEmail("admin@admin.com");
        u.setRg("123456789");
        u.setSenha("123456");
        u.setCelular("(54) 91234-1234");
        em.merge(u);
        
        Administrador a = new Administrador();
        a.setNome("João Administrador");
        a.setCpf("464.515.340-48");
        a.setEmail("admin@admin.com");
        a.setRg("8040506090");
        a.setSenha("admin");
        a.setCelular("(54) 99999-9999");
        em.merge(a);        
        
        Cliente c = new Cliente();
        c.setNome("Mariana Silva");
        c.setCpf("257.779.390-15");
        c.setFuncao("Investidora");
        c.setEmail("mariana@silva.com.br");
        c.setRg("3020106090");
        c.setSenha("123456");
        c.setCelular("(66) 96666-6666");
        em.merge(c);
        
        c.setNome("Osvaldo Madeira");
        c.setCpf("511.010.870-64");
        c.setFuncao("Empresário");
        c.setEmail("osvaldo@madeira.com.br");
        c.setRg("7050506090");
        c.setSenha(Encrypt.encryptMd5("123456"));
        c.setCelular("(11) 98765-4321");
        em.merge(c);

        em.getTransaction().commit();
	}
}
