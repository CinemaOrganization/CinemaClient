package cinema.client.entity;

import java.time.LocalTime;

public class Film {

    private long id;
    private String name;
    private String studio;
    private LocalTime length;
    private String description;

    public Film() {}

    public Film(String name, LocalTime length, String studio) {
        this.name = name;
        this.length = length;
        this.studio = studio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public LocalTime getLength() {
        return length;
    }

    public void setLength(LocalTime length) {
        this.length = length;
    }
    public void setLength(int hours, int minutes) {
        this.length = LocalTime.of(hours,minutes);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (id != film.id) return false;
        if (name != null ? !name.equals(film.name) : film.name != null) return false;
        if (studio != null ? !studio.equals(film.studio) : film.studio != null) return false;
        if (length != null ? !length.equals(film.length) : film.length != null) return false;
        return description != null ? description.equals(film.description) : film.description == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (studio != null ? studio.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studio='" + studio + '\'' +
                ", length=" + length +
                ", description='" + description + '\'' +
                '}';
    }
}
