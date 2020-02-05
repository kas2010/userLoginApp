import java.time.LocalDate;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String repassword;
    private LocalDate born;
    private Integer counter;

    public User(String id, String name, String surname, String login, String password, String repassword, String born, String counter) {
        try {
            this.id = id == null ? null : Long.parseLong(id);
        } catch (Exception e) {}
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.repassword = repassword;
        try {
            this.born = born == null || born.trim().equals("") ? null : LocalDate.parse(born);
        } catch (Exception e) {}
        try {
            this.counter = counter == null ? 0 : Integer.parseInt(counter);
        } catch (Exception e) {}
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
