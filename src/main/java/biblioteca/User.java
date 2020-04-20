package biblioteca;

public class User {
    String libraryNumber;
    String password;
    String name;
    String email;
    String phone;
    String role;
    boolean logged = false;

    public User(String libraryNumber, String password, String name, String email, String phone, String role) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public void loginUser() {
        this.logged = true;


    }


}
