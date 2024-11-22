package eu.iba.auto_test.converterbnb.dao.model;

public class Author {

    // Id пользователя
    private Long authorId;

    // ФИО пользователя
    private String authorFullName;

    // Логин Active Directory
    private String authorLoginAD;

    public Author() {
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Author setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public Author setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
        return this;
    }

    public String getAuthorLoginAD() {
        return authorLoginAD;
    }

    public Author setAuthorLoginAD(String authorLoginAD) {
        this.authorLoginAD = authorLoginAD;
        return this;
    }
}
