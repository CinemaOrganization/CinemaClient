package cinema.client.entity;

import cinema.client.secure.validation.annotation.NotEmptyLocalTime;
import cinema.client.secure.validation.annotation.ValidName;
import org.apache.commons.io.IOUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.repository.query.parser.Part;


import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Film",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name","year","studio"}))
public class Film {

    @Id
    @Column(name = "id_film")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @ValidName
    @Column(nullable = false)
    private String name;

    @ValidName
    @Column(nullable = false)
    private String studio;

    @Type(type="cinema.client.entity.unsupported.LocalTimeUserType")
    @NotEmptyLocalTime
    @Column(nullable = false)
    private LocalTime duration;

    @ValidName
    @Column
    private String description;

    @Column(nullable = false)
    private int year;

    @OneToMany(mappedBy = "film",cascade = CascadeType.REMOVE)
    private List<Session> sessions;

    @OneToMany(mappedBy = "film",cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Column
    private String imageId;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "image")
    private byte[] image;

    public Film() {}

    public Film(String name, String studio, LocalTime duration, String description, int year) {
        this.name = name;
        this.studio = studio;
        this.duration = duration;
        this.description = description;
        this.year = year;
    }

    public void uploadImage(javax.servlet.http.Part file){
        if (file != null) {
            byte[] fileContent = null;
            try {
                InputStream inputStream = file.getInputStream();
                fileContent = IOUtils.toByteArray(inputStream);
                setImage(fileContent);
            } catch (IOException е) {
            }
        }
    }
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (id != film.id) return false;
        if (year != film.year) return false;
        if (name != null ? !name.equals(film.name) : film.name != null) return false;
        if (studio != null ? !studio.equals(film.studio) : film.studio != null) return false;
        if (duration != null ? !duration.equals(film.duration) : film.duration != null) return false;
        return description != null ? description.equals(film.description) : film.description == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (studio != null ? studio.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studio='" + studio + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", year=" + year +
                '}';
    }
}
