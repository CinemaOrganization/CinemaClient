package cinema.client.SetupData;

import cinema.client.entity.Cinema;
import cinema.client.entity.Film;
import cinema.client.entity.Hall;
import cinema.client.entity.Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Конфигурация временных данных используемых для тестирования
 */
public class SessionSetupData {

    public static List<Session> setupData() {
        Cinema cinema = new Cinema("Заневский Каскад");
        Cinema cinema1 = new Cinema("Галерея");
        Cinema cinema2 = new Cinema("Жемчужина");

        List<Hall> halls = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Cinema cinemaLoop = i > 2 ? i > 5 ? cinema : cinema1 : cinema2;
            halls.add(new Hall(cinemaLoop, i, 100, true));
        }

        List<Film> films = new ArrayList<>();
        films.add(new Film("Бетмен", "DC", LocalTime.of(2, 20), "Фильм про героя...", 2010));
        films.add(new Film("Cупермен", "DC", LocalTime.of(3, 00), "Фильм про супергероя...", 2011));


        List<Session> sessions = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            Hall hallLoop = halls.get(i % halls.size());
            Film filmLoop = films.get(i % 2);
            sessions.add(new Session(hallLoop, filmLoop, hallLoop.getCinema(), 100
                    , LocalTime.of(i % 23 + 1, i % 59 + 1), LocalDate.now().plusDays(i%2)));
        }
        return sessions;
    }
}
