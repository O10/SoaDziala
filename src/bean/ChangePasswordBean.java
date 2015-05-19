package bean;

import model.User;
import service.UserDaoService;
import servlet.ChangePasswordServlet;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.servlet.http.HttpServletRequest;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * Created by O10 on 19.05.15.
 */
@ManagedBean(name = "changePassBean")
@RolesAllowed("ADMIN")
@RequestScoped
public class ChangePasswordBean {
    @EJB
    UserDaoService userDaoService;

    private String changePasswordUser;

    private String newPassword;



    private User[] users;


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public User[] getUsers() {
        List<User> allUsers = userDaoService.getAllUsers();

        System.out.println("Size is "+allUsers.size());

        users=new User[allUsers.size()];
        for(int i=0;i<allUsers.size();i++){
            users[i]=allUsers.get(i);
        }
        return users;
    }


    public String getChangePasswordUser() {
        return changePasswordUser;
    }

    public void setChangePasswordUser(String changePasswordUser) {
        this.changePasswordUser = changePasswordUser;
    }

    public String save(){
        userDaoService.updateUserPass(changePasswordUser, ChangePasswordServlet.sha256(newPassword));
        return "changeanypass";
    }
}
