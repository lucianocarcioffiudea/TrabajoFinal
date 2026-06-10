package rutasturisticas.api.dominio.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "parada", uniqueConstraints = { @UniqueConstraint(columnNames = { "idruta", "nombre" }) })
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "parada_id_seq")
    @SequenceGenerator(name = "parada_id_seq", sequenceName = "parada_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "orden", nullable = false)
    private int orden;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    // Muchas paradas pertenecen a una única Ruta
    @ManyToOne
    @JoinColumn(name = "idruta", nullable = false, foreignKey = @ForeignKey(name = "fkparada_idruta"))
    private Ruta ruta;

    @Column(name = "longitud", nullable = false, precision = 10, scale = 7)
    private BigDecimal longitud;

    @Column(name = "latitud", nullable = false, precision = 10, scale = 7)
    private BigDecimal latitud;

    // Mapeado como Integer asumiendo que guarda la duración estimada en minutos
    @Column(name = "tiempo")
    private Integer tiempo;

    // Usamos columnDefinition = "TEXT" para que soporte descripciones largas de
    // forma óptima en Postgres
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    public Parada() {
    }

    public Parada(int id, int orden, String nombre, Ruta ruta, BigDecimal longitud, BigDecimal latitud, Integer tiempo,
            String descripcion) {
        this.id = id;
        this.orden = orden;
        this.nombre = nombre;
        this.ruta = ruta;
        this.longitud = longitud;
        this.latitud = latitud;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
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

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
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

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}