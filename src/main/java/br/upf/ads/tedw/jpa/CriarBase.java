package br.upf.ads.tedw.jpa;

import javax.persistence.EntityManager;

import br.upf.ads.tedw.beans.Usuario;
import br.upf.ads.tedw.suport.Encrypt;

public class CriarBase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        Usuario u = new Usuario();
        u.setNome("Admin");
        u.setCpf("421.794.710-09");
        u.setEmail("admin@admin.com");
        u.setRg("123456789");
        u.setSenha(Encrypt.encryptMd5("admin"));
        u.setCelular("54 1234 1234");
        em.merge(u);
        em.getTransaction().commit();
	}

}
