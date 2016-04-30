package cinema.client.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @Column(name = "id_ticket")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private  long id;

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
        if (!session.equals(ticket.session)) return false;
        if (!film.equals(ticket.film)) return false;
        if (!hall.equals(ticket.hall)) return false;
        return cinema.equals(ticket.cinema);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + session.hashCode();
        result = 31 * result + film.hashCode();
        result = 31 * result + hall.hashCode();
        result = 31 * result + cinema.hashCode();
        result = 31 * result + number;
        result = 31 * result + row;
        return result;
    }
}
