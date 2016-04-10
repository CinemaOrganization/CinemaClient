package cinema.client.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "hall")
public class Hall {

    @Id
    @Column(name = "id_hall")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cinema")
    private Cinema cinema;

    @Column(nullable = false)
    private int number;

    @Column(name = "places_number",nullable = false)
    private int placesNumber;

    @Column(name = "is_3d",nullable = false)
    private boolean ThreeD;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hall_place",
            joinColumns = { @JoinColumn(name = "id_hall") },
            inverseJoinColumns = { @JoinColumn(name = "id_place") })
    private Set<Place> placeSet = new TreeSet<Place>();


    public Set<Place> getPlaceSet() {
        return placeSet;
    }

    public void setPlaceSet(TreeSet<Place> placeSet) {
        this.placeSet = placeSet;
    }
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

    public boolean getThreeD() {
        return ThreeD;
    }

    public void setThreeD(boolean threeD) {
        this.ThreeD = threeD;
    }

    public Hall() {}

    public Hall(Cinema cinema, int number, int placesNumber, boolean ThreeD) {
        this.cinema = cinema;
        this.number = number;
        this.placesNumber = placesNumber;
        this.ThreeD = ThreeD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hall hall = (Hall) o;

        if (id != hall.id) return false;
        if (number != hall.number) return false;
        if (placesNumber != hall.placesNumber) return false;
        if (ThreeD != hall.ThreeD) return false;
        return cinema != null ? cinema.equals(hall.cinema) : hall.cinema == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (cinema != null ? cinema.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + placesNumber;
        result = 31 * result + (ThreeD ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", cinema=" + cinema +
                ", number=" + number +
                ", placesNumber=" + placesNumber +
                ", ThreeD=" + ThreeD +
                '}';
    }
}
