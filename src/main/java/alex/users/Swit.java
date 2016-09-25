package alex.users;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SWIT")
public class Swit implements Comparable<Swit> {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "login")
    private Account author;
    @Column(name = "MESSAGE_BODY", length = 100000)
    private String messageBody;
    @Column(name = "DATE")
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "swit")
    private List<Comment> commentsList;
    public Swit() {
        date=new Date();
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Swit swit = (Swit) o;

        if (!author.equals(swit.author)) return false;
        if (date != null ? !date.equals(swit.date) : swit.date != null) return false;
        if (!messageBody.equals(swit.messageBody)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + messageBody.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Swit o) {
        return -this.date.compareTo(o.getDate());
    }

    public List<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }
}
