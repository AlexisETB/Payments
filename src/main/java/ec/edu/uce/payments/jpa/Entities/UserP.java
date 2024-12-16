package ec.edu.uce.payments.jpa.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users")
public class UserP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, unique = true, length = 15)
    private String phone;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createData;

    @Temporal(TemporalType.TIMESTAMP)
    private Date uptadeData;

    @PrePersist
    protected void onCreate() {
        createData = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        uptadeData = new Date();
    }

    public UserP() {}

    public UserP(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getUptadeData() {
        return uptadeData;
    }

    public void setUptadeData(Date uptadeData) {
        this.uptadeData = uptadeData;
    }

    public Date getCreateData() {
        return createData;
    }

    public void setCreateData(Date createData) {
        this.createData = createData;
    }
}
