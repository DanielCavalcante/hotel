package br.unipe.dsw.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URI;

public class ControleAcessoInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        if(uri.endsWith("login") || uri.endsWith(".css")
                                || uri.endsWith(".js")
                                || uri.endsWith(".ico")
                                || uri.endsWith("autenticar")){
            return true;
        }

        if(session.getAttribute("usuario") != null) {
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }
}
