package cinema.client.entity;

public class Hall {

    private long id;
    private Cinema cinema;
    private int number;
    private int placesNumber;
    private boolean is3d;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }

    public boolean is3d() {
        return is3d;
    }

    public void setIs3d(boolean is3d) {
        this.is3d = is3d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hall hall = (Hall) o;

        if (id != hall.id) return false;
        if (number != hall.number) return false;
        if (placesNumber != hall.placesNumber) return false;
        if (is3d != hall.is3d) return false;
        return cinema != null ? cinema.equals(hall.cinema) : hall.cinema == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (cinema != null ? cinema.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + placesNumber;
        result = 31 * result + (is3d ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", cinema=" + cinema +
                ", number=" + number +
                ", placesNumber=" + placesNumber +
                ", is3d=" + is3d +
                '}';
    }
}
