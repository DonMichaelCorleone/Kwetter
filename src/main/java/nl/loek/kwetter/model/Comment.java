package nl.loek.kwetter.model;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name= "Comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private GregorianCalendar date;

    private String poster;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Posting post;

    public Comment(String content, String author, Posting p) {
        this.content = content;
        this.date = new GregorianCalendar();
        this.post = p;
        this.poster = author;
    }

    public Comment() {

    }

    public Comment(Long id, String content, String u, Posting p) {
        this.id = id;
        this.content = content;
        this.date = new GregorianCalendar();
        this.post = p;
        this.poster = u;
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

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
