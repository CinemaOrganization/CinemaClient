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
    public void saveCinemas(Iterable<Cinema> cinemas){
        cinemaRepository.save(cinemas);
    }

    public List<Cinema> findAll(){
        return cinemaRepository.findAll();
    }

    public Cinema findOne(long id){
        return cinemaRepository.findOne(id);
    }

    public  void delete(long id){
        cinemaRepository.delete(id);
    }

    public Cinema findByName(String name){
        return  cinemaRepository.findByName(name);
    }

    public boolean isExistedCinema(Cinema cinema){
        Cinema existedCinema =cinemaRepository.findByName(cinema.getName());
        if (existedCinema != null){
            return true;
        }else {
            return false;
        }
    }

    public boolean isAnotherExistedCinema(Cinema cinema){
        Cinema foundCinema = cinemaRepository.findByName(cinema.getName());
        if (foundCinema != null && foundCinema.getId() != cinema.getId()){
            return true;
        }else {
            return false;
        }
    }
}
