package cinema.client.service;


import cinema.client.data.FilmRepository;
import cinema.client.data.SessionRepository;
import cinema.client.data.TicketRepository;
import cinema.client.entity.Film;
import cinema.client.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class StatisticSeviceImpl implements IStatisticService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<FilmStatistic> getBestFilms(int count, LocalDate from, LocalDate to) {
        List<Session> byDateBetween = sessionRepository.findByDateBetween(from, to);
        List<FilmStatistic> statistic = byDateBetween.stream()
                .map(s -> {
                    int size = ticketRepository.findBySession(s).size();
                    FilmStatistic filmStatistic = new FilmStatistic(s.getFilm(), size, s.getCost() * size);
                    return filmStatistic;
                }).collect(groupingBy(FilmStatistic::getFilm))
                .entrySet().stream()
                .map(e -> e.getValue().stream().reduce(new FilmStatistic(e.getKey(), 0, 0),
                        (f1, f2) -> {
                            f1.setSummaryCost(f1.getSummaryCost() + f2.getSummaryCost());
                            f1.setTicketsCount(f1.getTicketsCount() + f2.getTicketsCount());
                            return f1;
                        }))
                .sorted((e1, e2) -> e1.getTicketsCount() - e2.getTicketsCount())
                .limit(count)
                .collect(toList());
        return statistic;
    }

    @Override
    public List<CinemaStatistic> getBestCinema(LocalDate from, LocalDate to) {
        List<Session> byDateBetween = sessionRepository.findByDateBetween(from, to);
        List<CinemaStatistic> statistic = byDateBetween.stream()
                .map(s -> {
                    int size = ticketRepository.findBySession(s).size();
                    CinemaStatistic cinemaStatistic = new CinemaStatistic(s.getCinema(), size, s.getCost() * size);
                    return cinemaStatistic;
                }).collect(groupingBy(CinemaStatistic::getCinema))
                .entrySet().stream()
                .map(e -> e.getValue().stream().reduce(new CinemaStatistic(e.getKey(), 0, 0),
                        (f1, f2) -> {
                            f1.setSummaryCost(f1.getSummaryCost() + f2.getSummaryCost());
                            f1.setTicketsCount(f1.getTicketsCount() + f2.getTicketsCount());
                            return f1;
                        }))
                .sorted((e1, e2) -> e1.getTicketsCount() - e2.getTicketsCount())
                .collect(toList());
        return statistic;
    }

    @Override
    public List<CinemaStatistic> getBestCinema(int count, LocalDate from, LocalDate to) {
        List<Session> byDateBetween = sessionRepository.findByDateBetween(from, to);
        List<CinemaStatistic> statistic = byDateBetween.stream()
                .map(s -> {
                    int size = ticketRepository.findBySession(s).size();
                    CinemaStatistic cinemaStatistic = new CinemaStatistic(s.getCinema(), size, s.getCost() * size);
                    return cinemaStatistic;
                }).collect(groupingBy(CinemaStatistic::getCinema))
                .entrySet().stream()
                .map(e -> e.getValue().stream().reduce(new CinemaStatistic(e.getKey(), 0, 0),
                        (f1, f2) -> {
                            f1.setSummaryCost(f1.getSummaryCost() + f2.getSummaryCost());
                            f1.setTicketsCount(f1.getTicketsCount() + f2.getTicketsCount());
                            return f1;
                        }))
                .sorted((e1, e2) -> e1.getTicketsCount() - e2.getTicketsCount())
                .limit(count)
                .collect(toList());
        return statistic;
    }

    @Override
    public List<FilmStatistic> getBestFilm(LocalDate from, LocalDate to) {
        List<Session> byDateBetween = sessionRepository.findByDateBetween(from, to);
        List<FilmStatistic> statistic = byDateBetween.stream()
                .map(s -> {
                    int size = ticketRepository.findBySession(s).size();
                    FilmStatistic filmStatistic = new FilmStatistic(s.getFilm(), size, s.getCost() * size);
                    return filmStatistic;
                }).collect(groupingBy(FilmStatistic::getFilm))
                .entrySet().stream()
                .map(e -> e.getValue().stream().reduce(new FilmStatistic(e.getKey(), 0, 0),
                        (f1, f2) -> {
                            f1.setSummaryCost(f1.getSummaryCost() + f2.getSummaryCost());
                            f1.setTicketsCount(f1.getTicketsCount() + f2.getTicketsCount());
                            return f1;
                        }))
                .sorted((e1, e2) -> e1.getTicketsCount() - e2.getTicketsCount())
                .collect(toList());
        return statistic;
    }
    
}
