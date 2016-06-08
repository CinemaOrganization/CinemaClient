package cinema.client.SetupData;

import cinema.client.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Конфигурация временных данных используемых для тестирования
 */
public class SetupData {

    List<Hall> halls;
    List<Film> films;
    List<Session> sessions;
    List<Role> roles;
    List<User> users;
    List<Comment> comments;
    List<Ticket> tickets;

    public SetupData() {
        setupData();
    }

    public void setupData() {
        Cinema cinema = new Cinema("Заневский Каскад");
        Cinema cinema1 = new Cinema("Галерея");
        Cinema cinema2 = new Cinema("Жемчужина");

        halls = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Cinema cinemaLoop = i > 2 ? i > 5 ? cinema : cinema1 : cinema2;
            halls.add(new Hall(i, cinemaLoop, 3, 5, true));
        }

        films = new ArrayList<>();
        films.add(new Film("Бетмен", "DC", LocalTime.of(2, 20), "Фильм про героя...", 2010));
        films.add(new Film("Cупермен", "DC", LocalTime.of(3, 00), "Фильм про супергероя...", 2011));


        sessions = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            Hall hall = halls.get(i % halls.size());
            Film film = films.get(i % films.size());
            sessions.add(new Session(hall, film, hall.getCinema(), 100
                    , LocalTime.of(i % 23 + 1, i % 59 + 1), LocalDate.now().plusDays(i % 2)));
        }

        roles = new ArrayList<>();
        roles.add(new Role("ROLE_USER"));
        roles.add(new Role("ROLE_ADMIN"));


        users = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            User user = new User("firstName" + i, "firstName" + i,
                    "mail" + i + "@m.ru", "nick" + i, "12345");
            user.setAuthorities(new HashSet<>(roles));
            users.add(user);
        }

        comments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Comment comment = new Comment();
            comment.setId(i);
            comment.setText("Test comment message " + i);
            comment.setTime(LocalDateTime.now());
            comment.setFilm(films.get(i % films.size()));
            comment.setUser(users.get(i % users.size()));
            comments.add(comment);
        }

        tickets = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            Ticket ticket = new Ticket();
            ticket.setNumber(8 + i % 2);
            ticket.setRow(16 + i % 5);
            Session session = sessions.get(i % sessions.size());
            ticket.setSession(session);
            ticket.setUser(users.get(i % users.size()));
            ticket.setHall(session.getHall());
            ticket.setCinema(session.getCinema());
            ticket.setFilm(session.getFilm());
        }

    }

    public List<Hall> getHalls() {
        return halls;
    }

    public List<Film> getFilms() {
        return films;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
