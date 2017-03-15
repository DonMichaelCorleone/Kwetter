package nl.loek.kwetter.dto;


import java.util.GregorianCalendar;
import java.util.List;
import nl.loek.kwetter.model.Comment;
import nl.loek.kwetter.model.User;

public class PostingDTO {

    private Long id;

    private UserDTO author;

    private String title;
    private String content;

    private GregorianCalendar date;

    private List<Comment> comments;


    public PostingDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
