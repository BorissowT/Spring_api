package Springapi.springapi.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "owner")
@EntityListeners(AuditingEntityListener.class)
public class OwnerModel {

    @Id
    @Column(name = "OWNER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "YEARLY_PROFIT")
    private int profit;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "STORE_OWNER_ID", referencedColumnName = "OWNER_ID")
    List<StoreModel> stores = new ArrayList<>();

//ID
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //////
//NAME
    public String getNAME() {
        return name;
    }

    public void setNAME(String name) {
        this.name = name;
    }
    //////
//PROFIT
    public int getPROFIT() {
        return profit;
    }

    public void setPROFIT(int profit) {
        this.profit = profit;
    }
    //////
//STORES
    public List<StoreModel> getStores() {
        return stores;
    }

    public void setStores(List<StoreModel> stores) {
        this.stores = stores;
    }


}