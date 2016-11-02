package cinema.client.service;

import cinema.client.entity.Cinema;
import cinema.client.entity.Film;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IStatisticService {

    public List<FilmStatistic> getBestFilms(int count, LocalDate from, LocalDate to);

    public List<CinemaStatistic> getBestCinema(int count, LocalDate from, LocalDate to);

    public List<CinemaStatistic> getBestCinema(LocalDate from, LocalDate to);

    public List<FilmStatistic> getBestFilm(LocalDate from, LocalDate to);


    class FilmStatistic{
        private Film film;
        private int ticketsCount;
        private double SummaryCost;

        public FilmStatistic(Film film, int ticketsCount, double summaryCost) {
            this.film = film;
            this.ticketsCount = ticketsCount;
            SummaryCost = summaryCost;
        }

        public Film getFilm() {
            return film;
        }

        public void setFilm(Film film) {
            this.film = film;
        }

        public int getTicketsCount() {
            return ticketsCount;
        }

        public void setTicketsCount(int ticketsCount) {
            this.ticketsCount = ticketsCount;
        }

        public double getSummaryCost() {
            return SummaryCost;
        }

        public void setSummaryCost(double summaryCost) {
            SummaryCost = summaryCost;
        }
    }

    class CinemaStatistic{
        private Cinema cinema;
        private int ticketsCount;
        private double SummaryCost;

        public CinemaStatistic(Cinema cinema, int ticketsCount, double summaryCost) {
            this.cinema = cinema;
            this.ticketsCount = ticketsCount;
            SummaryCost = summaryCost;
        }

        public Cinema getCinema() {
            return cinema;
        }

        public void setCinema(Cinema cinema) {
            this.cinema = cinema;
        }

        public int getTicketsCount() {
            return ticketsCount;
        }

        public void setTicketsCount(int ticketsCount) {
            this.ticketsCount = ticketsCount;
        }

        public double getSummaryCost() {
            return SummaryCost;
        }

        public void setSummaryCost(double summaryCost) {
            SummaryCost = summaryCost;
        }
    }

    enum DayTime{
        MONING,AFTERNOON,EVNING,NIGHT
    }
}

