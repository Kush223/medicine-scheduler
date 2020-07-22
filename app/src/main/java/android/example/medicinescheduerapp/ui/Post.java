package android.example.medicinescheduerapp.ui;

public class Post {
    private String email;
    private String password;
    private String token;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getToken() {
        return token;
    }
    public Post(String email, String password,String token){
        this.email=email;
        this.password=password;
        this.token=token;
    }
}
