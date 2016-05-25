package cinema.client.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "hall",
        uniqueConstraints = @UniqueConstraint(columnNames = {"number","id_cinema"}))
public class Hall {

    @Id
    @Column(name = "id_hall")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;


    @Column(nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    private Cinema cinema;

    @Column(nullable = false)
    private int numberInRows;

    @Column(nullable = false)
    private int rows;

    @Column(name = "is_3d",nullable = false)
    private boolean ThreeD;

    public Hall(){}
    public Hall(int number, Cinema cinema, int numberInRows, int rows, boolean threeD) {
        this.number = number;
        this.cinema = cinema;
        this.numberInRows = numberInRows;
        this.rows = rows;
        ThreeD = threeD;
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


    public boolean isThreeD() {
        return ThreeD;
    }

    public void setThreeD(boolean threeD) {
        ThreeD = threeD;
    }

    public int getNumberInRows() {
        return numberInRows;
    }

    public void setNumberInRows(int numberInRows) {
        this.numberInRows = numberInRows;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
