package be.thomasmore.travelmore;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    public AuthFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);

            String reqURI = req.getRequestURI();

            if (reqURI.contains("login.xhtml")
                    || reqURI.contains("register.xhtml")
                    || reqURI.contains("index.xhtml")
                    || (ses != null && ses.getAttribute("id") != null)
                    || reqURI.contains("javax.faces.resource"))
                chain.doFilter(request, response);
            else
                res.sendRedirect(req.getContextPath() + "/login.xhtml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
