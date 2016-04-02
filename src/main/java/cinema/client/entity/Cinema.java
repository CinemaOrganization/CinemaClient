package cinema.client.entity;

public class Cinema {
    private long id;
    private String Name;

    public Cinema(String name) {
        Name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cinema cinema = (Cinema) o;

        return id == cinema.id && (Name != null ? Name.equals(cinema.Name) : cinema.Name == null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (Name != null ? Name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
