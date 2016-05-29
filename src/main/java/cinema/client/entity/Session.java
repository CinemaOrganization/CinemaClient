package cinema.client.entity;

import cinema.client.secure.validation.annotation.NotEmptyLocalTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "session",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_hall","id_film","id_cinema","date"}))
public class Session {

    @Id
    @Column(name = "id_session")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "id_hall")
    private Hall hall;

    @ManyToOne()
    @JoinColumn(name = "id_film")
    private Film film;

    @ManyToOne()
    @JoinColumn(name = "id_cinema")
    private Cinema cinema;

    @Min(value = 0)
    @Column(nullable = false)
    private double cost;

    @NotEmptyLocalTime
    @Type(type = "cinema.client.entity.unsupported.LocalTimeUserType")
    @Column(nullable = false)
    private LocalTime time;

    @Type(type = "cinema.client.entity.unsupported.LocalDateUserType")
    @Column(nullable = false)
    private LocalDate date;
    //еще что-то??


    public Session() {}

    public Session(Hall hall, Film film, Cinema cinema, double cost, LocalTime time, LocalDate date) {
        this.hall = hall;
        this.film = film;
        this.cinema = cinema;
        this.cost = cost;
        this.time = time;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (id != session.id) return false;
        if (Double.compare(session.cost, cost) != 0) return false;
        if (hall != null ? !hall.equals(session.hall) : session.hall != null) return false;
        if (film != null ? !film.equals(session.film) : session.film != null) return false;
        if (cinema != null ? !cinema.equals(session.cinema) : session.cinema != null) return false;
        if (time != null ? !time.equals(session.time) : session.time != null) return false;
        return date != null ? date.equals(session.date) : session.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (hall != null ? hall.hashCode() : 0);
        result = 31 * result + (film != null ? film.hashCode() : 0);
        result = 31 * result + (cinema != null ? cinema.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", hall=" + hall +
                ", film=" + film +
                ", cinema=" + cinema +
                ", cost=" + cost +
                ", time=" + time +
                ", date=" + date +
                '}';
    }
}


