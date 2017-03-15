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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import nl.loek.kwetter.model.Comment;
import nl.loek.kwetter.model.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name= "Posting")
@NamedQueries({
    @NamedQuery(name = "Posting.findAll", query = "select p from Posting as p"),
    @NamedQuery(name = "Posting.findByUserName", query = "select p from Posting as p where p.author = :author"),
})
public class Posting implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private User author;
    
    private String title;
    private String content;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private GregorianCalendar date;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments;
    
    private Long nextCommentId;
  
    public Posting(){
        
    }
    
    public Posting(User author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = new GregorianCalendar();
        this.comments = new ArrayList<Comment>();
        this.nextCommentId = 1L;
    }


    public List<Comment> getComments() {
        return comments;
    }
    
    public void addComment(String message , User author) {
        Comment comment = new Comment(this.nextCommentId++, message, author, this);
        this.comments.add(comment);
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
