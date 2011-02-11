package Controle;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class FiltroProd implements Filter {

    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try {

            if (httpRequest.getSession().getAttribute("Login") == null) {

                httpRequest.setAttribute("MsgErro", "Para cadastrar um produto é preciso estar \"logado\" no sistema.");
                httpRequest.getRequestDispatcher("/login.jsp").forward(request, response);
            }

            else {
                chain.doFilter(request, response);
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
    public void destroy(){
    }

}
