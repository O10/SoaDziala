package model;

import javax.persistence.*;

/**
 * Created by O10 on 19.05.15.
 */

@Entity
@Table(name = "users", schema = "public", catalog = "soa")
@NamedQueries({
        @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.username = :username")
})
public class User {
    private String username;
    private String passwd;


    @Column(name = "passwd")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
