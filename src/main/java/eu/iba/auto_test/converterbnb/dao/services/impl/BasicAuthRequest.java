package eu.iba.auto_test.converterbnb.dao.services.impl;

public class BasicAuthRequest {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public BasicAuthRequest setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BasicAuthRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
