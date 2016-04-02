package cinema.client.entity;

import java.time.LocalDateTime;

public class Session {

    private long id;
    private Hall hall;
    private Film film;
    private double cost;
    private LocalDateTime time;
    //еще что-то??


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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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
        return time != null ? time.equals(session.time) : session.time == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (hall != null ? hall.hashCode() : 0);
        result = 31 * result + (film != null ? film.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", hall=" + hall +
                ", film=" + film +
                ", cost=" + cost +
                ", time=" + time +
                '}';
    }
}

