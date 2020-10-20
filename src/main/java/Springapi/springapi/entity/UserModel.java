package Springapi.springapi.entity;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, updatable = false)
    private Long id;

    @Column(name="LOGIN", nullable = false)
    private String login;

    @Column(name="hash", nullable = false)
    private String passHash;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin(){
        return login;
    }

    public String getHash(){
        return passHash;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setHash(String passHash){
        this.passHash = passHash;
    }

}