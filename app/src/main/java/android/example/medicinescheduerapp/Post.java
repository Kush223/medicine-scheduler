package android.example.medicinescheduerapp;

public class Post {
    private String email;
    private String password;
    private String token;
    private String name;
    private String address;
    private String field;
    private String phone;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getField() {
        return field;
    }

    public Post(String email, String password, String token, String name, String phone, String address,String field){
        this.email=email;
        this.password=password;
        this.token=token;
        this.name= name;
        this.phone = phone;
        this.address = address;
        this.field = field;
    }
}
