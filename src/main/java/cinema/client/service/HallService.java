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

    private HallRepository hallRepository;
    private SessionService sessionService;

    @Autowired
    public HallService(HallRepository hallRepository,
                       SessionService sessionService) {
        this.hallRepository = hallRepository;
        this.sessionService = sessionService;
    }

    public Set<Hall> findBySessions(List<Session> sessions) {
        return sessions.stream().map(Session::getHall).collect(Collectors.toSet());
    }

    public Hall findOne(long id){
        return hallRepository.findOne(id);
    }
    public List<Hall> findAll(){
        return hallRepository.findAll();
    }

    public void save(Iterable<Hall> halls){
        hallRepository.save(halls);
    }

    public void delete(Long id){
        hallRepository.delete(id);
    }

    public Hall getHallBySession(long session_id) {
        Session session = sessionService.findOne(session_id);
        return session.getHall();
    }
}
