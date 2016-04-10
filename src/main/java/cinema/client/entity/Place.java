package cinema.client.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Table(name = "Place")
public class Place implements Comparable<Place>{

    @Id
    @Column(name = "id_place")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "number")
    private int number;

    @Column(name = "row")
    private int row;

    public int getRow() {
        return row;
    }


 /*   @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "hall_place",
            joinColumns = { @JoinColumn(name = "id_place") },
            inverseJoinColumns = { @JoinColumn(name = "id_hall") })
    private java.util.Set<Hall> hallSet = new HashSet<Hall>();

    public java.util.Set<Hall> gethallSet() {
        return hallSet;
    }

    public void setHallSet(java.util.Set<Hall> hallSet) {
        this.hallSet = hallSet;
    }*/
    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Place o) {

        return number - o.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (number != place.number) return false;
        return row == place.row;

    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + row;
        return result;
    }
}
