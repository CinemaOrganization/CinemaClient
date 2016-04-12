package cinema.client.service;

import cinema.client.data.CinemaRepository;
import cinema.client.entity.Cinema;
import cinema.client.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CinemaService {

    CinemaRepository cinemaRepository;

    @Autowired
    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public Set<Cinema> findBySessions(List<Session> sessions) {
        return sessions.stream().map(Session::getCinema).collect(Collectors.toSet());
    }
}
