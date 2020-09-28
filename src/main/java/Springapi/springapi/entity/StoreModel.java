package Springapi.springapi.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@EntityListeners(AuditingEntityListener.class)
public class StoreModel {

    @Id
    @Column(name = "STORE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CAPACITY_SQM")
    private Integer capacity;

    @Column(name = "STORE_OWNER_ID")
    private Integer owner;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "CLIENT_STORE_ID", referencedColumnName = "STORE_ID")
    List<ClientModel> clients = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "AUTHOR_STORE_ID", referencedColumnName = "STORE_ID")
    List<AuthorModel> authors = new ArrayList<>();
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
//CAPACITY
    public int getCAPACITY() {
        return capacity;
    }

    public void setCAPACITY(int capacity) {
        this.capacity = capacity;
    }
    //////
//FK of OWNER
    public int getOWNER() {
    return owner;
}

    public void setOWNER(int owner) {
        this.owner = owner;
    }
    //////
//CLIENTS
    public List<ClientModel> getClients() {
    return clients;
}

    public void setClients(List<ClientModel> clients) {
        this.clients = clients;
    }
    //////
//AUTHORS
    public List<AuthorModel> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorModel> authors) {
        this.authors = authors;
    }

}