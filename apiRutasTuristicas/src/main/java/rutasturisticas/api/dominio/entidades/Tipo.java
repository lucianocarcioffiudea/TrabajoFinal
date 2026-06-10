package rutasturisticas.api.dominio.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "tipo_id_seq")
    @SequenceGenerator(name = "tipo_id_seq", sequenceName = "tipo_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre;

    public Tipo() {
    }

    public Tipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}