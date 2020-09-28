package Springapi.springapi.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "book")
@EntityListeners(AuditingEntityListener.class)
public class BookModel {

    @Id
    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ISSUE_YEAR")
    private Integer iyear;

    @Column(name = "BOOK_AUTHOR_ID")
    private Integer author;

//ID
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    //////
//TITLE
    public String getTITLE() {
        return title;
    }

    public void setTITLE(String title) {
        this.title = title;
    }

//ISSUE YEAR
    public Integer getIYEAR() {
        return iyear;
    }

    public void setIYEAR(Integer iyear) {
        this.iyear = iyear;
    }
    //////
//AUTHOR
    public Integer getAUTHOR() {
        return author;
    }

    public void setAUTHOR(Integer author) {
        this.author = author;
    }
    //////
}
