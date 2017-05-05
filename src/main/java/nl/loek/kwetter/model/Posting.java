package nl.loek.kwetter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Posting")
@NamedQueries({
    @NamedQuery(name = "Posting.findAll", query = "select p from Posting as p")
    ,
    @NamedQuery(name = "Posting.findByUserName", query = "select p from Posting as p where p.author = :author"),})
public class Posting implements Serializable, Comparable<Posting> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private String author;

    private String title;
    private String content;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private GregorianCalendar date;
    

    public Posting() {

    }

    public Posting(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = new GregorianCalendar();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.content;
    }

    @Override
    public int compareTo(Posting o) {
        return this.getDate().compareTo(o.getDate());
    }
}
