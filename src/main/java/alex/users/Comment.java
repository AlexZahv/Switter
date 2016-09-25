package alex.users;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class Comment implements Comparable<Comment> {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "name")
    private Account author;
    @ManyToOne
    @JoinColumn(name = "id")
    private Swit swit;
    @Column(name = "Content")
    private String content;
    @Column(name = "Date")
    private Date dateComment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public Swit getSwit() {
        return swit;
    }

    public void setSwit(Swit swit) {
        this.swit = swit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (!author.equals(comment.author)) return false;
        if (!content.equals(comment.content)) return false;
        if (!swit.equals(comment.swit)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + author.hashCode();
        result = 31 * result + swit.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public int compareTo(Comment o) {
        return this.dateComment.compareTo(o.dateComment);
    }
}
