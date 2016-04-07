package cinema.client.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "Film")
public class Film {

    @Id
    @Column(name = "id_film")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String studio;

    @Type(type="cinema.client.entity.unsupported.LocalTimeUserType")
    @Column(nullable = false)
    private LocalTime duration;

    @Column
    private String description;

    @Column(nullable = false)
    private int year;

    public Film() {}

    public Film(String name, String studio, LocalTime duration, String description, int year) {
        this.name = name;
        this.studio = studio;
        this.duration = duration;
        this.description = description;
        this.year = year;
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
