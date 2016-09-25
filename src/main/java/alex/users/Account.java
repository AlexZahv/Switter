package alex.users;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @Column(name = "login", nullable = false, unique = true)
    @Size(min = 4, message = "Min length is 4 symbols")
    private String login;
    @Column(name = "PASSWORD", nullable = false)
    @Size(min = 6, message = "Min length is 6 symbols")
    private String password;
    @Column(name = "EMAIL")
    @Email
    private String emailAddress;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "author")
    private List<Swit> switList;
    @Column(name = "ADMIN")
    private boolean isAdmin;
    @Column(name = "PHOTO_PATH")
    private String photoPath;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "description", length = 10000)
    private String shortDescription;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "FOLLOWERS_ACCOUNT",
            joinColumns = {@JoinColumn(name = "LOGIN")},
            inverseJoinColumns = {@JoinColumn(name = "BUDDY_LOGIN")})
    private Set<Account> followers = new HashSet<>();
    @ManyToMany(mappedBy = "followers")
    private Set<Account> buddies = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "author")
    private List<Comment> commentsList;
    @Column(name = "photo")
    private byte[] photo;

    public Account() {
    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String year, String month, String day) {
        GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        birthDate = calendar.getTime();
    }

    public List<Swit> getSwitList() {
        return switList;
    }

    public void setSwitList(List<Swit> twitList) {
        this.switList = twitList;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Account> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Account> followers) {
        this.followers = followers;
    }

    public Set<Account> getBuddies() {
        return buddies;
    }

    public void setBuddies(Set<Account> buddies) {
        this.buddies = buddies;
    }

    public List<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }
}
