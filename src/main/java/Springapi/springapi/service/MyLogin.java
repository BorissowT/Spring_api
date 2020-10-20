package Springapi.springapi.service;
import Springapi.springapi.entity.UserModel;
import org.springframework.security.core.authority.AuthorityUtils;

public class MyLogin extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private UserModel user;

    public MyLogin(UserModel user) {
        super(user.getLogin(), user.getHash(), AuthorityUtils.createAuthorityList("ALL"));
        this.user = user;
    }

}
