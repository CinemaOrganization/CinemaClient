package cinema.client.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Ticket",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_session","number","row"}))
public class Ticket {

    @Id
    @Column(name = "id_ticket")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "id_film")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "id_hall")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    private Cinema cinema;

    @Column(name = "number")
    private int number;

    @Column(name = "row")
    private int row;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (id != ticket.id) return false;
        if (number != ticket.number) return false;
        if (row != ticket.row) return false;
        return session != null ? session.equals(ticket.session) : ticket.session == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (session != null ? session.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + row;
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", session=" + session +
                ", film=" + film +
                ", hall=" + hall +
                ", cinema=" + cinema +
                ", number=" + number +
                ", row=" + row +
                ", user=" + user +
                '}';
    }
}
