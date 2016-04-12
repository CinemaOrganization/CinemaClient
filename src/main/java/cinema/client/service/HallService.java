package cinema.client.service;

import cinema.client.data.HallRepository;
import cinema.client.entity.Hall;
import cinema.client.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HallService {

    HallRepository hallRepository;

    @Autowired
    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public Set<Hall> findBySessions(List<Session> sessions) {
        return sessions.stream().map(Session::getHall).collect(Collectors.toSet());
    }
}
