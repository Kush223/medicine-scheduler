package android.example.medicinescheduerapp.ui;

public class Post {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Post(String email, String password){
        this.email=email;
        this.password=password;
    }
}
