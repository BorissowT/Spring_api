package Springapi.springapi.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@EntityListeners(AuditingEntityListener.class)
public class ClientModel {

    @Id
    @Column(name = "CLIENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FNAME")
    private String fname;

    @Column(name = "LNAME")
    private String lname;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CLIENT_STORE_ID")
    private Integer store;


//ID
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    //////
//ADDRESS
    public String getADDRESS() {
        return address;
    }

    public void setADDRESS(String address) {
        this.address = address;
    }
    //////
//FNAME
    public String getFNAME() {
        return fname;
    }

    public void setFNAME(String fname) {
        this.fname = fname;
    }
    //////
//LNAME
    public String getLNAME() {
        return lname;
    }

    public void setLNAME(String lname) {
        this.lname = lname;
    }
    //////
//EMAIL
    public String getEMAIL() {
        return email;
    }

    public void setEMAIL(String email) {
        this.email = email;
    }
    //////
//STORE
    public Integer getSTORE() {
        return store;
    }

    public void setSTORE(Integer store) {
        this.store = store;
    }
    //////
}
