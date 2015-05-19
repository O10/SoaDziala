package servlet;

import model.User;
import service.UserDaoService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

/**
 * Created by O10 on 19.05.15.
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = "/change.do")
public class ChangePasswordServlet extends HttpServlet {
    @EJB
    UserDaoService userDaoService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String remoteUser = request.getRemoteUser();

        User userByLogin = userDaoService.getUserByLogin(remoteUser);

        String newPass = request.getParameter("newpasswd");
        String oldPasswd = request.getParameter("oldpasswd");
        String newPassConfirm = request.getParameter("newpasswdconfirm");

        if (!newPass.equals(newPassConfirm)) {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.write("Róźne hasła");
            return;
        }

        String oldHash = sha256(oldPasswd);

        if (!userByLogin.getPasswd().equals(oldHash)) {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.write("Złe stare hasło");
            return;
        }

        this.userDaoService.updateUserPass(userByLogin.getUsername(),sha256(newPass));
        request.logout();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");


            byte[] hash = digest.digest(base.getBytes());

            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
