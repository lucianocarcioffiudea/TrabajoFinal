package rutasturisticas.api.dominio.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ciudad", uniqueConstraints = { @UniqueConstraint(columnNames = { "idpais", "nombre" }) })
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ciudad_id_seq")
    @SequenceGenerator(name = "ciudad_id_seq", sequenceName = "ciudad_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    // Muchas ciudades pertenecen a un País
    @ManyToOne
    @JoinColumn(name = "idpais", nullable = false, foreignKey = @ForeignKey(name = "fkciudad_idpais"))
    private Pais pais;

    @Column(name = "longitud", nullable = false, precision = 10, scale = 7)
    private BigDecimal longitud;

    @Column(name = "latitud", nullable = false, precision = 10, scale = 7)
    private BigDecimal latitud;

    public Ciudad() {
    }

    public Ciudad(int id, String nombre, Pais pais, BigDecimal longitud, BigDecimal latitud) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.longitud = longitud;
        this.latitud = latitud;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

}