package br.upf.ads.tedw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.upf.ads.tedw.controller.LoginController;

@WebFilter(urlPatterns = { "/faces/Privado/*" })
public class FiltroLogin implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession sessao = httpRequest.getSession();
		String contextPath = httpRequest.getContextPath();

		LoginController lc = (LoginController) sessao.getAttribute("loginController");
		if ((lc == null) || (lc.getUsuarioLogado() == null)) {
			System.out.println("Redirecionar para : " + contextPath + "/faces/Login/LoginForm.xhtml");
			httpResponse.sendRedirect(contextPath + "/faces/Login/LoginForm.xhtml");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}