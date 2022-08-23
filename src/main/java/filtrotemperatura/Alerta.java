package filtrotemperatura;

import java.util.Objects;

public class Alerta {
    private long id;
    private String description;

    public Alerta() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        Alerta alerta = (Alerta) o;
        return id == alerta.id && Objects.equals(description, alerta.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
