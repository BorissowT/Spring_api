package Springapi.springapi.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@EntityListeners(AuditingEntityListener.class)
public class AuthorModel {

    @Id
    @Column(name = "AUTHOR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FNAME")
    private String fname;

    @Column(name = "LNAME")
    private String lname;

    @Column(name = "BIRTH_YEAR")
    private Integer byear;

    @Column(name = "AUTHOR_STORE_ID")
    private Integer store;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "BOOK_AUTHOR_ID", referencedColumnName = "AUTHOR_ID")
    List<BookModel> books = new ArrayList<>();

//ID
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
//BIRTH YEAR
    public Integer getBYEAR() {
        return byear;
    }

    public void setBYEAR(Integer byear) {
        this.byear = byear;
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
//BOOKS
    public List<BookModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
    }

}
