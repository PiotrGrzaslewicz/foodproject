package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/app/*")
public class AuthorizationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest)request).getSession();
        Object adminId = session.getAttribute("adminId");

        if (adminId==null) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect("/login");
        } else {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            request.setAttribute("adminId", session.getAttribute("adminId"));
            AdminDao adminDao = new AdminDao();
            String adminFirstName = adminDao.findById((Integer) session.getAttribute("adminId")).getFirstName();
            session.setAttribute("adminName", adminFirstName);
            request.setAttribute("adminName", adminFirstName);
            chain.doFilter(request, response);
        }
    }
}