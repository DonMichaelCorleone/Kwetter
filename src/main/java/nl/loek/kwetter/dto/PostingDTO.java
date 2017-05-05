package nl.loek.kwetter.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PostingDTO {

    private Long id;

    private String author;

    private String title;
    private String content;

    private GregorianCalendar date;

    public PostingDTO() {

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

        return sdf.format(this.date.getTime());
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

}
