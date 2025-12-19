package xyz.mywebs.imusic.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false)
    private String thirdPartID;

    public User() {}

    public User(String username, String password ,String platform ,String thirdPartID) {
        this.username = username;
        this.password = password;
        this.platform = platform;
        this.thirdPartID=thirdPartID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlatform(){
        return platform;
    }

    public void setPlatform(String platform){
        this.platform=platform;
    }

    public String getThirdPartID(){
        return thirdPartID;
    }

    public void setThirdPartID(String thirdPartID){
        this.thirdPartID=thirdPartID;
    }
}
