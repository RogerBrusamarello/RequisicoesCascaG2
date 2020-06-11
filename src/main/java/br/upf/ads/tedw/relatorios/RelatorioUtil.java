package br.upf.ads.tedw.relatorios;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

import br.upf.ads.tedw.jpa.JPAUtil;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author jaqson
 */
public class RelatorioUtil {
    
	/**
	 * M�todo responsável por rodar um relatório do jasper e responder uma requisição JSF com um PDF
	 * @param pathRelatorio Caminho de onde está o relatório no contexto da aplicação
	 * @param parameters são os parâmetros a serem repassados para o relatório executar
	 * @throws SQLException
	 */
	public static void rodarRelatorioPDF(String pathRelatorio, HashMap parameters) throws SQLException {
		try {
			Connection con = getEntityManagerJDBCConnection();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
			JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/"+pathRelatorio), parameters, con);
			
			byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
			
			HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			res.setContentType("application/pdf");
			res.setHeader("Content-disposition", "inline;filename=relatorio.pdf"); // diretamente na p�gina
			// res.setHeader("Content-disposition", "attachment;filename=arquivo.pdf"); // baixar ou salvar
			res.getOutputStream().write(b);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * Método responsável por obter uma conexão JDBC a partir de uma EntityManager.
	 * Funciona com Hibernate 5.2 ou superior.
	 * @return conexão JDBC com o banco de dados
	 * @throws SQLException
	 */
    public static Connection getEntityManagerJDBCConnection() throws SQLException { 
        EntityManager em = JPAUtil.getEntityManager();
        @SuppressWarnings("resource")
		Session s = (Session) em;
        SessionImplementor sim = (SessionImplementor) s;
        Connection conexao = sim.connection();
        em.close();
        return conexao; 
    }
    
	/* para versões mais antigas do Hibernate ...  
    public static Connection getEntityManagerJDBCConnection() throws SQLException { 
    	EntityManager em = getEntityManager(); 
    	HibernateEntityManager hem = (HibernateEntityManager) em;
    	SessionImplementor sim = (SessionImplementor) hem.getSession();
    	Connection conexao = sim.connection();
    	em.close();
    	return conexao; 
    }  
	 */

    // ou, mais antigo ainda fazer assim ...
	/*
	public static Connection getEntityManagerJDBCConnection() throws SQLException {
	   	EntityManager em = factory.createEntityManager();
	   	Session ses = (Session) em.getDelegate();
	   	SessionFactoryImpl sessionFactory = (SessionFactoryImpl) ses.getSessionFactory();
	   	Connection conexao = sessionFactory.getConnectionProvider().getConnection();	
	   	em.close(); 
	    return conexao;
	} 
	*/

}
